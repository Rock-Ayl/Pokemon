package com.rock.pokemon.gdx.model.manager;

import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import com.rock.pokemon.gdx.model.event.node.NpcMoveEventNode;
import com.rock.pokemon.gdx.model.event.node.SoundPlayEventNode;
import com.rock.pokemon.gdx.model.event.node.WaitingEventNode;
import com.rock.pokemon.gdx.model.ui.box.DialogueAndOptionBox;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
     * 初始化
     *
     * @param pokemonGame 游戏对象
     */
    public EventManager(PokemonGame pokemonGame) {
        this.pokemonGame = pokemonGame;
        this.eventQueue = new LinkedList<>();
        this.timer = 0f;
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
                isFinished = processNpcMove(delta, (NpcMoveEventNode) this.currentNode);
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
    private boolean processNpcMove(float delta, NpcMoveEventNode node) {
        //TODO 这里需要配合 Person 实现“脚本化移动”，暂时返回true直接跳过
        return true;
    }

    /**
     * 辅助：开启对话框(这部分逻辑从 BoxExecutor 迁移过来比较好，或者作为一种特殊的事件节点)
     * 这里演示如何让 EventManager 等待 UI 关闭
     */
    public boolean processDialogue(DialogueAndOptionBox dialogueBox) {
        //如果对话框已经结束
        if (dialogueBox.isFinished()) {
            //完成
            return true;
        }
        //未完成，阻塞
        return false;
    }

}
