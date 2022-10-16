package com.rock.pockmon.gdx.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pockmon.gdx.enums.StandEnum;
import com.rock.pockmon.gdx.model.map.TileMap;
import com.rock.pockmon.gdx.model.people.Person;

/**
 * 人物移动控制器,通过输入控制人物的移动
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class PersonMoveController extends InputAdapter {

    //要移动的人物
    private Person person;
    //当前地图网格
    private TileMap tileMap;

    /**
     * 初始化,指定要移动的人物
     *
     * @param person
     */
    public PersonMoveController(Person person, TileMap tileMap) {
        //记录人物及地图网格
        this.person = person;
        this.tileMap = tileMap;
    }

    @Override
    public boolean keyDown(int keycode) {
        //主角移动判定,同一次移动只能一个方向,按照绿宝石的手感判定, 上优先级最高,下其次,左第三,右的判定最低
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //向上走一格
            this.person.move(tileMap, 0, 1);
            //修改人物站立方向
            this.person.stand(StandEnum.NORTH);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //向下走一格
            this.person.move(tileMap, 0, -1);
            //修改人物站立方向
            this.person.stand(StandEnum.SOUTH);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //向左走一格
            this.person.move(tileMap, -1, 0);
            //修改人物站立方向
            this.person.stand(StandEnum.WEST);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //向右走一格
            this.person.move(tileMap, 1, 0);
            //修改人物站立方向
            this.person.stand(StandEnum.EAST);
        }
        //其他忽略
        return false;
    }

}
