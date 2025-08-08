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
 * 盒子事件 执行者,用来处理谈话
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
         * 获取玩家
         */

        //获取世界里,该人物
        Person adventurer = this.pokemonGame.getWorldScreen().getAdventurer();

        /**
         * 判断是否满足 盒子事件 条件
         */

        //如果行动不是站立
        if (adventurer.getActionState() != Person.ActionEnum.STAND) {
            //不执行
            return;
        }
        //根据人脸的方向,记录人脸前方的坐标
        int x = adventurer.getX() + adventurer.getFacingState().getDx();
        int y = adventurer.getY() + adventurer.getFacingState().getDy();
        //如果获取不到地图块
        if (this.pokemonGame.getWorldScreen().getWorld().getTileMap().getTile(x, y) == null) {
            //过
            return;
        }
        //获取世界上层的城镇中,事件的盒子
        DialogueAndOptionBox dialogueAndOptionBox = this.pokemonGame.getWorldScreen().getDialogueAndOptionBox();
        //如果没有结束
        if (dialogueAndOptionBox.isFinished() == false) {
            //过
            return;
        }

        /**
         * 获取各种事件,但只触发一个,目前只有人物身上的事件
         */

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
            return;
        }
        //获取人物对应事件
        NpcMapConfig.NpcMapNodeEvent event = Optional.ofNullable(person)
                //获取人物上的事件列表
                .map(Person::getEventList)
                //默认
                .orElse(new ArrayList<>())
                .stream()
                //todo 以后可以做事件的开关、以及优先级
                .findFirst()
                .orElse(null);
        //如果没有任何事件
        if (event == null) {
            //过
            return;
        }

        /**
         * 实现
         */

        //根据人物,让对应事件人物转头
        switch (adventurer.getFacingState()) {
            case SOUTH:
                //怼脸
                person.changeFacingDir(DirectionEnum.NORTH);
                break;
            case NORTH:
                //怼脸
                person.changeFacingDir(DirectionEnum.SOUTH);
                break;
            case WEST:
                //怼脸
                person.changeFacingDir(DirectionEnum.EAST);
                break;
            case EAST:
                //怼脸
                person.changeFacingDir(DirectionEnum.WEST);
                break;
        }

        //读取事件配置
        BoxMapConfig.BoxMapNode talkTestNode = this.pokemonGame.getMyAssetManager().getBoxMapConfig().getBoxMap().get(event.getBoxName());
        //这里直接用事件
        dialogueAndOptionBox.reset(talkTestNode);
        //开启
        dialogueAndOptionBox.nextNode();

    }

}