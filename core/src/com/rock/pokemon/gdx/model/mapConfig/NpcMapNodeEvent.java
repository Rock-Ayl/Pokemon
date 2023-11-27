package com.rock.pokemon.gdx.model.mapConfig;

/**
 * Npc 节点配置类事件 实体
 *
 * @Author ayl
 * @Date 2023-11-20
 */
public class NpcMapNodeEvent {

    //事件备注
    private String remark;

    //盒子名称
    private String boxName;

    /**
     * 以下为 get set 方法
     */

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

}