package com.rock.pockmon.gdx.model.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.rock.pockmon.gdx.enums.ActionEnum;
import com.rock.pockmon.gdx.enums.PersonEnum;
import com.rock.pockmon.gdx.enums.StandEnum;
import com.rock.pockmon.gdx.model.map.TileMap;

/**
 * 人物实体
 *
 * @Author ayl
 * @Date 2022-10-13
 */
public class Person {

    /**
     * 基本信息
     */

    //当前人物在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的),这个在人类实体里仅用来判定移动时和地图块之间的关系
    private int x;
    private int y;

    //当前人物在世界的真实坐标(考虑到移动、骑车、跑步等动作不是时刻在网格中,所采用的世界坐标,运营到补帧动画)
    private float worldX;
    private float worldY;

    //人物宽高
    private float width;
    private float height;

    //人物枚举
    private PersonEnum personEnum;
    //当前图片对象
    private Texture currentImage;

    /**
     * 移动参数
     */

    //当前状态(站立、走路、跑步、骑自行车、冲浪等等)
    private ActionEnum actionState;

    //起始坐标和目标坐标
    private int srcX, srcY;
    private int destX, destY;

    //当前已活动时间
    private float animTime;
    //动画时间,这里指0.5秒完成一次动画
    private float onceAnimTime = 0.25F;

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

        //默认人物为[站立]状态
        this.actionState = ActionEnum.STAND;

        //让人物图片设置为[站立_南]
        updateStand(StandEnum.SOUTH);

    }

    /**
     * 处理移动中时的动画(可以理解为补帧)
     *
     * @param delta 每帧的时间
     */
    public void moving(float delta) {
        //根据当前状态判定
        switch (actionState) {
            //todo
            case RUN:
                break;
            //todo
            case CYCLING:
                break;
            //todo
            case SURFING:
                break;
            //如果是走路
            case WALK:
                //叠加本次走路的动画时间
                animTime += delta;
                //计算出其真实的世界坐标,据说绿宝石是线性的,这里不太懂
                this.worldX = Interpolation.linear.apply(srcX, destX, animTime / onceAnimTime);
                this.worldY = Interpolation.linear.apply(srcY, destY, animTime / onceAnimTime);
                //如果动画时间结束了
                if (animTime >= onceAnimTime) {
                    //结束走路
                    walkEnd();
                }
                break;
            //站立或其他
            case STAND:
            default:
                //直接结束
                break;
        }
    }

    /**
     * 人物移动,有可能不成功
     *
     * @param tileMap 当前地图网格
     * @param x       x轴移动
     * @param y       y轴移动
     */
    public boolean move(TileMap tileMap, int x, int y) {
        //如果当前不是站着的
        if (actionState != ActionEnum.STAND) {
            //无法移动
            return false;
        }
        //计算下一步走到的位置
        int nextX = this.x + x;
        int nextY = this.y + y;
        //判断地图边界问题
        if (nextX < 0 || nextY < 0 || nextX >= tileMap.getWidth() || nextY >= tileMap.getHeight()) {
            //无法移动,固定为原来目标,但是不结束move判定,相当于走路了
            nextX = this.x;
            nextY = this.y;
        } else {
            //可以移动,更改人物动画及状态
            walkStart(this.x, this.y, x, y);
        }
        //真实移动
        this.x = nextX;
        this.y = nextY;
        //根据走路方向更改图片
        if (y > 0) {
            //修改人物站立方向
            updateStand(StandEnum.NORTH);
        } else if (y < 0) {
            //修改人物站立方向
            updateStand(StandEnum.SOUTH);
        } else if (x < 0) {
            //修改人物站立方向
            updateStand(StandEnum.WEST);
        } else if (x > 0) {
            //修改人物站立方向
            updateStand(StandEnum.EAST);
        }
        //移动成功
        return true;
    }

    //开始走路
    private void walkStart(int srcX, int srcY, int moveX, int moveY) {
        //初始化参数
        this.srcX = srcX;
        this.srcY = srcY;
        this.destX = srcX + moveX;
        this.destY = srcY + moveY;
        //初始化活动时间
        this.animTime = 0F;
        //改变人物状态为走路
        this.actionState = ActionEnum.WALK;
    }

    //结束走路
    private void walkEnd() {
        //改变人物状态为站立
        this.actionState = ActionEnum.STAND;
    }

    /**
     * 修改人物站立方向
     *
     * @param standEnum 站立枚举
     */
    public void updateStand(StandEnum standEnum) {
        //组装出默认人物图片path[人物图片目录+站立+默认方向南]
        String defaultImagePath = this.personEnum.getImageDir() + ActionEnum.STAND.getPath() + "/" + standEnum.getFileName();
        //设置当前图片对象为某个站立方向
        this.currentImage = new Texture(Gdx.files.internal(defaultImagePath));
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public PersonEnum getPersonEnum() {
        return personEnum;
    }

    public Texture getCurrentImage() {
        return currentImage;
    }

}
