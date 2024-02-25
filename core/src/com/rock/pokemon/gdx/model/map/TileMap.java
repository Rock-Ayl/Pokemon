package com.rock.pokemon.gdx.model.map;

import com.rock.pokemon.gdx.model.people.Person;
import lombok.Getter;
import lombok.Setter;

/**
 * 地图网格
 *
 * @Author ayl
 * @Date 2022-10-16
 */
@Getter
@Setter
public class TileMap {

    //地图网格宽高
    private int width;
    private int height;

    //地图网格本身
    private Tile[][] map;

    /**
     * 初始化地图网格,目前只有草
     *
     * @param width  宽
     * @param height 高
     */
    public TileMap(int width, int height) {

        //初始化地图网格
        this.map = new Tile[width][height];

        //初始化地图网格
        this.width = width;
        this.height = height;

    }

    /**
     * 为某一个单元格设置对应人物
     *
     * @param x      对应坐标
     * @param y      对应坐标
     * @param person 要设置的人物
     */
    public void setPerson(int x, int y, Person person) {
        //如果越界
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
            //过
            return;
        }
        //设置人物
        getTile(x, y).setPerson(person);
    }

    /**
     * 为某一个单元格删除对应人物
     *
     * @param x      对应坐标
     * @param y      对应坐标
     * @param person 要设置的人物
     */
    public void removePerson(int x, int y, Person person) {
        //如果越界
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
            //过
            return;
        }
        //删除人物
        getTile(x, y).removePerson(person);
    }

    /**
     * 获取精确坐标地图块
     *
     * @param x
     * @param y
     * @return
     */
    public Tile getTile(int x, int y) {
        return this.map[x][y];
    }

}
