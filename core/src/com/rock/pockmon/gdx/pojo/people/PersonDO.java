package com.rock.pockmon.gdx.pojo.people;

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
public class PersonDO extends Rectangle {

    //人物枚举
    private PersonEnum personEnum;
    //当前图片对象
    private Texture currentImage;

    //todo 人物移动速度,暂时统一为300
    public int moveSpeed = 300;

    /**
     * 使用人物枚举初始化
     *
     * @param personEnum 人物枚举
     */
    public PersonDO(PersonEnum personEnum) {

        //基本信息
        this.personEnum = personEnum;

        //默认人物为站立南
        changeStand(StandEnum.SOUTH);

        //初始化人物真实宽高
        this.width = 32;
        this.height = 48;

    }

    /**
     * 修改任务站立方向
     *
     * @param standEnum 站立枚举
     */
    public void changeStand(StandEnum standEnum) {
        //组装出默认人物图片path[人物图片目录+站立+默认方向南]
        String defaultImagePath = this.personEnum.getImageDir() + ActionEnum.stand.getPath() + "/" + standEnum.getFileName();
        //设置当前图片对象为某个站立方向
        this.currentImage = new Texture(Gdx.files.internal(defaultImagePath));
    }

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
