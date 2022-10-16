package com.rock.pockmon.gdx.model.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.rock.pockmon.gdx.enums.ActionEnum;
import com.rock.pockmon.gdx.enums.PersonEnum;
import com.rock.pockmon.gdx.enums.StandEnum;

/**
 * 人物实体
 *
 * @Author ayl
 * @Date 2022-10-13
 */
public class Person extends Rectangle {

    //人物枚举
    private PersonEnum personEnum;
    //当前图片对象
    private Texture currentImage;

    /**
     * 使用人物枚举初始化
     *
     * @param personEnum 人物枚举
     */
    public Person(PersonEnum personEnum) {

        //基本信息
        this.personEnum = personEnum;

        //设定用户宽高,绿宝石中,通常人物占接近1.5个地图网格
        this.width = 1.0F;
        this.height = 1.5F;

        //默认人物为站立南
        this.stand(StandEnum.SOUTH);

    }

    /**
     * 人物移动
     *
     * @param x x轴移动
     * @param y y轴移动
     */
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * 修改人物站立方向
     *
     * @param standEnum 站立枚举
     */
    public void stand(StandEnum standEnum) {
        //组装出默认人物图片path[人物图片目录+站立+默认方向南]
        String defaultImagePath = this.personEnum.getImageDir() + ActionEnum.stand.getPath() + "/" + standEnum.getFileName();
        //设置当前图片对象为某个站立方向
        this.currentImage = new Texture(Gdx.files.internal(defaultImagePath));
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public PersonEnum getPersonEnum() {
        return personEnum;
    }

    public void setPersonEnum(PersonEnum personEnum) {
        this.personEnum = personEnum;
    }

    public Texture getCurrentImage() {
        return currentImage;
    }

}
