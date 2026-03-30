package com.rock.pokemon.gdx.model.manager;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import com.rock.pokemon.gdx.model.event.node.*;
import com.rock.pokemon.gdx.model.map.Person;
import com.rock.pokemon.gdx.model.map.WorldObject;
import com.rock.pokemon.gdx.model.screen.WorldScreen;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 事件管理器
 * 负责统一调度、执行所有类型的事件节点
 *
 * @Author ayl
 * @Date 2025-02-05
 */
@Getter
public class EventManager {

    //游戏对象
    private PokemonGame pokemonGame;

    //当前待执行的事件队列
    private Queue<EventNodeTemplate> eventQueue;

    //当前正在执行的节点
    private EventNodeTemplate currentNode;

    /**
     * 节点状态记录
     */

    //计时器(用于等待事件等)
    private float timer;

    //是否刚刚开始执行当前节点(用于初始化)
    private boolean isNodeStart;

    /**
     * 节点临时状态 - NPC移动
     */

    //当前脚本移动方向序列
    private List<DirectionEnum> npcMoveDirectionList;
    //当前执行到的索引
    private int npcMoveDirectionIndex;

    /**
     * 初始化
     *
     * @param pokemonGame 游戏对象
     */
    public EventManager(PokemonGame pokemonGame) {
        this.pokemonGame = pokemonGame;
        this.eventQueue = new LinkedList<>();
        this.timer = 0f;
        this.npcMoveDirectionList = new ArrayList<>();
        this.npcMoveDirectionIndex = 0;
    }

    /**
     * 开始一组新的事件
     *
     * @param eventList 事件列表
     */
    public void startEvents(List<? extends EventNodeTemplate> eventList) {
        //判空
        if (CollectionUtils.isEmpty(eventList)) {
            //过
            return;
        }
        //清空旧的(如果需要强制中断旧事件，这里直接清空)
        this.eventQueue.clear();
        this.currentNode = null;
        //加入队列
        this.eventQueue.addAll(eventList);
        //重置临时状态
        resetNpcMoveState();
    }

    /**
     * 判断是否正在忙碌(有事件正在执行)
     *
     * @return
     */
    public boolean isBusy() {
        //判断
        return this.currentNode != null || this.eventQueue.isEmpty() == false;
    }

    /**
     * 每帧更新事件逻辑
     *
     * @param delta 帧时间
     */
    public void update(float delta) {

        /**
         * 1. 获取或切换节点
         */

        //如果当前没有节点执行
        if (this.currentNode == null) {
            //如果队列也是空的
            if (this.eventQueue.isEmpty()) {
                //没事做，直接返回
                return;
            }
            //取出下一个节点
            this.currentNode = this.eventQueue.poll();
            //标记为刚开始
            this.isNodeStart = true;
            //重置计时器
            this.timer = 0f;
        }

        /**
         * 2. 执行当前节点逻辑
         */

        //当前节点是否已完成
        boolean isFinished = false;
        //根据类型分发处理
        switch (this.currentNode.getType()) {
            //等待
            case WAITING:
                isFinished = processWaiting(delta, (WaitingEventNode) this.currentNode);
                break;
            //音效
            case SOUND_PLAY:
                isFinished = processSoundPlay((SoundPlayEventNode) this.currentNode);
                break;
            //NPC移动
            case NPC_MOVE:
                isFinished = processNpcMove((NpcMoveEventNode) this.currentNode);
                break;
            //NPC隐藏
            case NPC_HIDE:
                isFinished = processNpcHide((NpcHideEventNode) this.currentNode);
                break;
            //NPC显示
            case NPC_SHOW:
                isFinished = processNpcShow((NpcShowEventNode) this.currentNode);
                break;
            //播放事物动画
            case WORLD_OBJECT_ANIMATION_PLAY:
                isFinished = processWorldObjectAnimationPlay((WorldObjectAnimationPlayEventNode) this.currentNode);
                break;
            //场景切换淡入(变黑)
            case TRANSITION_SWITCH_ANIMATION_START_IN:
                isFinished = processTransitionSwitchAnimationStartIn((TransitionSwitchAnimationStartInEventNode) this.currentNode);
                break;
            //场景切换淡出(变亮)
            case TRANSITION_SWITCH_ANIMATION_START_OUT:
                isFinished = processTransitionSwitchAnimationStartOut((TransitionSwitchAnimationStartOutEventNode) this.currentNode);
                break;
            //切换场景
            case SCENE_CHANGE:
                isFinished = processSceneChange((SceneChangeEventNode) this.currentNode);
                break;
            //其他
            default:
                //未实现的类型直接跳过
                isFinished = true;
                break;
        }

        /**
         * 3. 如果完成，准备下一个
         */

        //如果完成了
        if (isFinished == true) {
            //重置需要临时状态的节点
            if (this.currentNode.getType() == EventNodeTypeEnum.NPC_MOVE) {
                resetNpcMoveState();
            }
            //置空当前节点，下一帧 update 会自动取下一个
            this.currentNode = null;
        }

    }

    /**
     * 处理：等待事件
     */
    private boolean processWaiting(float delta, WaitingEventNode node) {
        //叠加时间
        this.timer += delta;
        //如果超过了配置的时间
        if (this.timer >= node.getSecond()) {
            //完成
            return true;
        }
        //未完成
        return false;
    }

    /**
     * 处理：音效播放
     */
    private boolean processSoundPlay(SoundPlayEventNode node) {
        //如果是刚开始
        if (this.isNodeStart) {
            //播放一次音效
            this.pokemonGame.getGameContext().getMySoundManager().play(node.getSoundId());
            //标记已经初始化过了
            this.isNodeStart = false;
        }
        //音效事件通常是瞬间完成的，不阻塞
        return true;
    }

    /**
     * 处理：NPC移动
     * (这里需要你在 Person 中实现具体的移动指令逻辑，这里做演示)
     */
    private boolean processNpcMove(NpcMoveEventNode node) {
        //读取人物
        Person person = getNpcById(node.getNpcId());
        //人物不存在,直接跳过
        if (person == null) {
            //过
            return true;
        }
        //初始化本节点
        if (this.isNodeStart) {
            //构建方向序列
            this.npcMoveDirectionList = new ArrayList<>();
            //遍历方向序列
            for (Integer ordinal : Optional.ofNullable(node.getMoveDirectionEnumOrdinalList()).orElse(new ArrayList<>())) {
                //判空
                if (ordinal == null) {
                    continue;
                }
                //只接受合法的枚举下标
                if (ordinal < 0 || ordinal >= DirectionEnum.values().length) {
                    continue;
                }
                this.npcMoveDirectionList.add(DirectionEnum.values()[ordinal]);
            }
            //重置索引
            this.npcMoveDirectionIndex = 0;
            //初始化结束
            this.isNodeStart = false;
        }

        //没有任何可用方向,本节点完成
        if (CollectionUtils.isEmpty(this.npcMoveDirectionList)) {
            person.walkStop();
            return true;
        }

        //还有路要走
        if (this.npcMoveDirectionIndex < this.npcMoveDirectionList.size()) {
            //只有站立时才能发起下一步脚本移动
            if (person.canCheckAndTalk()) {
                //发起一步
                DirectionEnum directionEnum = this.npcMoveDirectionList.get(this.npcMoveDirectionIndex);
                person.changeFacingDir(directionEnum);
                person.move(directionEnum, WalkEnum.WALK);
                this.npcMoveDirectionIndex++;
            }
            //移动链尚未完成
            return false;
        }

        //方向已经发完了,还要等最后一步走完
        boolean isMoveFinished = person.canCheckAndTalk();
        //脚本移动结束后，立即切回站立，避免后续节点期间停在走路帧
        if (isMoveFinished) {
            //切回站立
            person.walkStop();
        }
        //返回
        return isMoveFinished;
    }

    /**
     * 处理：NPC隐藏
     */
    private boolean processNpcHide(NpcHideEventNode node) {
        //读取人物
        Person person = getNpcById(node.getNpcId());
        //如果存在
        if (person != null) {
            //隐藏
            person.setVisible(false);
        }
        //返回
        return true;
    }

    /**
     * 处理：NPC显示
     */
    private boolean processNpcShow(NpcShowEventNode node) {
        //读取人物
        Person person = getNpcById(node.getNpcId());
        //如果存在
        if (person != null) {
            //显示
            person.setVisible(true);
        }
        //返回
        return true;
    }

    /**
     * 处理：播放事物动画
     */
    private boolean processWorldObjectAnimationPlay(WorldObjectAnimationPlayEventNode node) {
        //获取当前世界
        WorldScreen worldScreen = this.pokemonGame.getWorldScreen();
        //判空
        if (worldScreen == null || worldScreen.getWorld() == null) {
            //过
            return true;
        }
        //读取目标事物
        WorldObject worldObject = worldScreen.getWorld().findWorldObjectById(node.getWorldObjectId());
        //判空
        if (worldObject == null) {
            //过
            return true;
        }
        //没有动画可播放,直接完成
        if (worldObject.getAnimation() == null) {
            //过
            return true;
        }
        //首次进入本节点时,设置强制播放次数
        if (this.isNodeStart) {
            //覆盖播放模式(默认乒乓)
            worldObject.getAnimation().setPlayMode(parseAnimationPlayMode(node.getWorldObjectPlayMode()));
            //每次触发都从头开始
            worldObject.setAnimationTimer(0F);
            //设置播放次数
            int forceTimes = Optional.ofNullable(node.getForceAnimationTimes()).orElse(1);
            worldObject.setForceAnimationTimes(Math.max(1, forceTimes));
            this.isNodeStart = false;
        }
        //等待动画播放完成
        return worldObject.getForceAnimationTimes() < 1;
    }

    /**
     * 解析枚举 动画播放模式
     *
     * @param playMode 动画播放模式
     * @return
     */
    private Animation.PlayMode parseAnimationPlayMode(String playMode) {
        //判空
        if (StringUtils.isBlank(playMode)) {
            //循环
            for (Animation.PlayMode value : Animation.PlayMode.values()) {
                //如果相同
                if (value.name().equals(playMode)) {
                    //返回
                    return value;
                }
            }
        }
        //默认
        return Animation.PlayMode.LOOP_PINGPONG;
    }

    /**
     * 处理：场景切换-淡入(变黑)
     */
    private boolean processTransitionSwitchAnimationStartIn(TransitionSwitchAnimationStartInEventNode node) {
        //读取当前场景
        WorldScreen worldScreen = this.pokemonGame.getWorldScreen();
        //没有世界时不阻塞
        if (worldScreen == null) {
            //过
            return true;
        }
        //首次进入本节点,触发淡入
        if (this.isNodeStart == true) {
            //触发淡入
            worldScreen.getTransitionSwitchAnimation().startFadingIn();
            this.isNodeStart = false;
        }
        //直到完全黑屏才算完成
        return worldScreen.getTransitionSwitchAnimation().isInDark();
    }

    /**
     * 处理：场景切换-淡出(变亮)
     */
    private boolean processTransitionSwitchAnimationStartOut(TransitionSwitchAnimationStartOutEventNode node) {
        //读取当前场景
        WorldScreen worldScreen = this.pokemonGame.getWorldScreen();
        //没有世界时不阻塞
        if (worldScreen == null) {
            //过
            return true;
        }
        //首次进入本节点,触发淡出
        if (this.isNodeStart == true) {
            worldScreen.getTransitionSwitchAnimation().startFadingOut();
            this.isNodeStart = false;
        }
        //直到淡出结束(回到等待态)才算完成
        return worldScreen.getTransitionSwitchAnimation().isWaiting();
    }

    /**
     * 处理：场景切换
     */
    private boolean processSceneChange(SceneChangeEventNode node) {
        //目标参数缺失,直接跳过
        if (node.getTargetWorldMapConfigPath() == null || node.getTargetX() == null || node.getTargetY() == null) {
            //过
            return true;
        }
        //记录切换前是否已经黑幕（用于跨场景继承）
        WorldScreen currentWorldScreen = this.pokemonGame.getWorldScreen();
        //判断是否需要保持黑幕
        boolean shouldKeepDarkMask = currentWorldScreen != null
                && currentWorldScreen.getTransitionSwitchAnimation() != null
                && currentWorldScreen.getTransitionSwitchAnimation().isInDark();
        //解析目标朝向
        DirectionEnum targetFacingDirectionEnum = DirectionEnum.parseByName(node.getTargetDirection());
        //切换场景
        WorldScreen nextWorldScreen = new WorldScreen(
                this.pokemonGame,
                node.getTargetWorldMapConfigPath(),
                node.getTargetX(),
                node.getTargetY(),
                targetFacingDirectionEnum
        );
        //设置新场景
        this.pokemonGame.setScreen(nextWorldScreen);
        //如果切换前已经黑幕，让新场景保持黑幕，再执行后续淡出
        if (shouldKeepDarkMask) {
            //保持黑幕
            nextWorldScreen.getTransitionSwitchAnimation().setInDarkImmediately();
        }
        //场景切换节点本身瞬间完成
        return true;
    }

    /**
     * todo 肯定要整理
     * 读取人物
     * npcId = -1 表示主角
     */
    private Person getNpcById(Integer npcId) {
        //读取当前场景
        WorldScreen worldScreen = this.pokemonGame.getWorldScreen();
        //判空
        if (worldScreen == null) {
            //过
            return null;
        }
        //主角
        if (npcId != null && npcId == -1) {
            //返回主角
            return worldScreen.getAdventurer();
        }
        //普通npc
        if (npcId == null || worldScreen.getWorld() == null) {
            //过
            return null;
        }
        //返回普通npc
        return worldScreen.getWorld().getPersonMap().get(String.valueOf(npcId));
    }

    /**
     * 重置NPC移动临时状态
     */
    private void resetNpcMoveState() {
        this.npcMoveDirectionList = new ArrayList<>();
        this.npcMoveDirectionIndex = 0;
    }

}