package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * Box节点 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
public class BoxMapNode {

    //备注
    private String remark;

    //盒子列表
    private List<BoxMapNodeBox> boxList;

    /**
     * 以下为 get set 方法
     */

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<BoxMapNodeBox> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<BoxMapNodeBox> boxList) {
        this.boxList = boxList;
    }

}