package com.rock.pokemon.gdx.model.ui.box;

import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.model.map.Person;
import com.rock.pokemon.gdx.model.map.Tile;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.map.config.BoxMapConfig;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig;

import java.util.ArrayList;
import java.util.Optional;

/**
 * 交互执行者
 * 负责检测玩家面前的物体，并触发对应的事件链
 *
 * @Author ayl
 * @Date 2024-04-26
 */
public class BoxExecutor {

    //游戏对象
    private final PokemonGame pokemonGame;

    /**
     * 初始化实现
     *
     * @param pokemonGame 游戏对象
     */
    public BoxExecutor(PokemonGame pokemonGame) {
        this.pokemonGame = pokemonGame;
    }

    /**
     * 检查/对话
     */
    public void checkAndTalk() {

        /**
         * 状态检查
         */

        //如果事件管理器正在忙(例如上一个剧情还没跑完)
        if (this.pokemonGame.getGameContext().getEventManager().isBusy() == true) {
            //不执行新的交互
            return;
        }

        /**
         * 获取玩家
         */

        //获取世界里,该人物
        Person adventurer = this.pokemonGame.getWorldScreen().getAdventurer();
        //判断人物是否允许该操作
        if (adventurer.canCheckAndTalk() == false) {
            //不执行
            return;
        }

        /**
         * 寻找交互目标
         */

        //根据人脸的方向,记录人脸前方的坐标
        int x = adventurer.getX() + adventurer.getFacingState().getDx();
        int y = adventurer.getY() + adventurer.getFacingState().getDy();
        //如果获取不到地图块
        if (this.pokemonGame.getWorldScreen().getWorld().getTileMap().getTile(x, y) == null) {
            //过
            return;
        }

        /**
         * 1. 尝试触发 NPC 事件
         */

        //尝试触发
        boolean triggered = triggerNpcEvent(x, y, adventurer);
        //如果触发了
        if (triggered == true) {
            //过
            return;
        }

        //todo 2. 尝试触发 告示牌/物体 事件 (后续扩展)

    }

    /**
     * 触发NPC事件
     * * @param x 目标X
     *
     * @param y          目标Y
     * @param adventurer 玩家
     * @return 是否触发成功
     */
    private boolean triggerNpcEvent(int x, int y, Person adventurer) {

        //获取前面的人物
        Person person = Optional.ofNullable(this.pokemonGame.getWorldScreen().getWorld())
                //获取地图块矩阵
                .map(World::getTileMap)
                //获取对应目的地
                .map(p -> p.getTile(x, y))
                //获取人物
                .map(Tile::getPerson)
                .orElse(null);

        //如果没有人物
        if (person == null) {
            //过
            return false;
        }

        /**
         * 简单的交互逻辑：让NPC转身看玩家
         */

        //让对应事件人物转头
        switch (adventurer.getFacingState()) {
            case SOUTH:
                person.changeFacingDir(DirectionEnum.NORTH);
                break;
            case NORTH:
                person.changeFacingDir(DirectionEnum.SOUTH);
                break;
            case WEST:
                person.changeFacingDir(DirectionEnum.EAST);
                break;
            case EAST:
                person.changeFacingDir(DirectionEnum.WEST);
                break;
        }

        /**
         * 读取事件列表并执行
         */

        //获取配置时间
        NpcMapConfig.NpcMapNodeEvent event = Optional.ofNullable(person)
                .map(Person::getEventList)
                .orElse(new ArrayList<>())
                .stream()
                .findFirst()
                .orElse(null);
        //如果没有配置事件
        if (event == null) {
            //过
            return false;
        }

        //读取盒子配置
        BoxMapConfig.BoxMapNode talkNode = this.pokemonGame
                .getGameContext()
                .getMyAssetManager()
                .getBoxMapConfig()
                .getBoxMap()
                .get(event.getBoxName());

        //重置并开启对话框
        DialogueAndOptionBox dialogueBox = this.pokemonGame.getWorldScreen().getDialogueAndOptionBox();
        dialogueBox.reset(talkNode);
        dialogueBox.nextNode();

        //返回
        return true;
    }

}