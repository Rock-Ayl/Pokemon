package com.rock.pokemon.gdx.model.map;

import com.rock.pokemon.gdx.model.people.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 世界对象,代表一个世界(比如未白镇)
 *
 * @Author ayl
 * @Date 2022-10-21
 */
public class World {

    //地图网格本身
    private TileMap tileMap;

    //该世界的人物列表
    private List<Person> personList;

    //该世界的事物的列表(树木、草、牌子等等)
    private List<WorldObject> worldObjectList;

    /**
     * 初始化世界
     *
     * @param width  世界宽
     * @param height 世界高
     */
    public World(int width, int height) {

        //初始化地图网格
        this.tileMap = new TileMap(width, height);

        personList = new ArrayList<>();
        worldObjectList = new ArrayList<>();
    }

    /**
     * 加入人物
     *
     * @param person 人物实体
     */
    public void addPerson(Person person) {
        //todo
    }

    /**
     * 加入事物
     *
     * @param worldObject 事物实体
     */
    public void addWorldObject(WorldObject worldObject) {
        //todo
    }

    /**
     * 每帧更新
     *
     * @param delta 每帧时间
     */
    public void update(float delta) {
        //todo
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public TileMap getTileMap() {
        return tileMap;
    }

}
