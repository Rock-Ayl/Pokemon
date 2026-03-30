package com.rock.pokemon.gdx.model.controller;


import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.map.Person;
import com.rock.pokemon.gdx.model.map.Tile;
import com.rock.pokemon.gdx.model.map.WorldObject;
import com.rock.pokemon.gdx.model.map.config.EventMapConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

/**
 * 玩家命令处理器
 *
 * @Author ayl
 * @Date 2025-11-15
 */
public class PlayerCommandHandler {

    //人物
    private final Person person;

    //游戏对象
    private final PokemonGame pokemonGame;

    /**
     * 输入 - 按键状态
     * 这里只记录真正关心的 key，不再用 300 大数组
     */

    //方向相关集合
    private static final LinkedHashSet<Integer> DIR_INPUT_KEY_LINKED_SET = new LinkedHashSet<>(Arrays.asList(
            Settings.INPUT_KEY_UP,
            Settings.INPUT_KEY_DOWN,
            Settings.INPUT_KEY_LEFT,
            Settings.INPUT_KEY_RIGHT
    ));

    // 按住不移动的阈值
    private static final float NOT_MOVE_TIME = 0.15F;

    // ---- 状态记录 ----

    /**
     * 输入-按键状态
     */

    //输入状态,状态可以全部存在,比如按上的同时也可以按下,但是怎么处理就我们说的算了(目前300够用了)
    private boolean[] buttonPressedArray = new boolean[300];

    //输入状态的持续时间(目前300够用了)
    private float[] buttonTimeArr = new float[300];

    /**
     * 初始化
     *
     * @param person 角色
     */
    public PlayerCommandHandler(PokemonGame pokemonGame, Person person) {
        this.pokemonGame = pokemonGame;
        this.person = person;
    }

    /**
     * 每帧更新移动逻辑
     */
    public void update(float delta) {
        //是否按下方向键
        boolean anyDirPressed = false;
        //按“绿宝石手感”优先级：上 > 下 > 左 > 右
        for (Integer key : DIR_INPUT_KEY_LINKED_SET) {
            //如果未按下
            if (isPressed(key) == false) {
                //本轮过
                continue;
            }
            //记录其按下了
            anyDirPressed = true;
            //获取方向
            DirectionEnum directionEnum = DirectionEnum.parseByKeycode(key);
            //无论如何,人物先转向
            this.person.changeFacingDir(directionEnum);
            //叠加按下时间
            addPressTime(key, delta);
            //按下时间还没到阈值：只转向不移动
            if (getPressTime(key) <= NOT_MOVE_TIME) {
                //本轮过
                continue;
            }
            //先尝试触发门事件、固定坐标场景联通事件
            if (tryTriggerDoorEvent(directionEnum) || tryTriggerFixedMapConnectEvent(directionEnum)) {
                //触发后本帧不再继续处理移动
                break;
            }
            //计算走路 / 跑步
            WalkEnum walkEnum = this.buttonPressedArray[Settings.INPUT_KEY_CANCEL] ? WalkEnum.RUN : WalkEnum.WALK;
            //人物真正尝试移动
            this.person.move(directionEnum, walkEnum);
            //本帧只允许一个方向，结束判定
            break;
        }
        //没有任何方向键按着
        if (anyDirPressed == false) {
            //尝试停止走路
            this.person.walkStop();
        }
    }

    /**
     * 按下
     */
    public void onKeyDown(int keycode) {
        //输入
        this.buttonPressedArray[keycode] = true;
    }

    /**
     * 取消按下
     */
    public void onKeyUp(int keycode) {
        //输出
        this.buttonPressedArray[keycode] = false;
    }

    /**
     * 获取 按下状态
     *
     * @param keycode
     * @return
     */
    private boolean isPressed(int keycode) {
        //返回
        return this.buttonPressedArray[keycode];
    }

    /**
     * 叠加 按下时间
     *
     * @param keycode 键位
     * @return
     */
    private void addPressTime(int keycode, float delta) {
        //叠加
        this.buttonTimeArr[keycode] += delta;
    }

    /**
     * 获取 按下时间
     *
     * @param keycode 键位
     * @return
     */
    private float getPressTime(int keycode) {
        //返回
        return this.buttonTimeArr[keycode];
    }

    /**
     * 尝试触发门事件(前方地图块的事物上挂载 doorEvent)
     *
     * @param directionEnum 当前输入方向
     * @return 是否触发
     */
    private boolean tryTriggerDoorEvent(DirectionEnum directionEnum) {
        //获取目标坐标
        int targetX = this.person.getX() + directionEnum.getDx();
        int targetY = this.person.getY() + directionEnum.getDy();
        //获取目标地图块
        Tile tile = Optional.ofNullable(this.person.getWorld())
                .map(p -> p.getTileMap().getTile(targetX, targetY))
                .orElse(null);
        //判空
        if (tile == null) {
            //过
            return false;
        }
        //读取门事物与事件
        EventMapConfig.Event doorEvent = Optional.ofNullable(tile)
                .map(Tile::getWorldObject)
                .map(WorldObject::getDoorEvent)
                .orElse(null);
        //无事件直接结束
        if (doorEvent == null || CollectionUtils.isEmpty(doorEvent.getEventNodeList())) {
            //过
            return false;
        }
        //启动事件链
        this.pokemonGame.getGameContext().getEventManager().startEvents(doorEvent.getEventNodeList());
        //触发成功
        return true;
    }

    /**
     * todo 早晚得改
     * 固定坐标联通：
     * 路比家(1F) 8,0 / 9,0 站立后按下可离开到未白镇
     */
    private boolean tryTriggerFixedMapConnectEvent(DirectionEnum directionEnum) {
        //仅处理按下离开
        if (directionEnum != DirectionEnum.SOUTH) {
            //过
            return false;
        }
        //只在固定点生效
        int x = this.person.getX();
        int y = this.person.getY();
        //判断是否在固定点
        if ((x == 8 || x == 9) == false || y != 0) {
            //过
            return false;
        }
        //触发离开事件链
        return startEventById("little_root_ruby_first_move_out");
    }

    /**
     * 根据事件ID触发事件链
     *
     * @param eventId 事件ID
     * @return 是否成功触发
     */
    private boolean startEventById(String eventId) {
        //读取事件配置
        EventMapConfig.Event event = Optional.ofNullable(this.pokemonGame)
                .map(PokemonGame::getGameContext)
                .map(p -> p.getMyAssetManager().getEventMapConfig())
                .map(EventMapConfig::getEventMap)
                .map(p -> p.get(eventId))
                .orElse(null);
        //无效事件
        if (event == null || CollectionUtils.isEmpty(event.getEventNodeList())) {
            //过
            return false;
        }
        //启动
        this.pokemonGame.getGameContext().getEventManager().startEvents(event.getEventNodeList());
        //触发成功
        return true;
    }

}