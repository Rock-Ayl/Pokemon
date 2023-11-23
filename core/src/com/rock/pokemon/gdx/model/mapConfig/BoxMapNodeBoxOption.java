package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * Box节点盒子 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
public class BoxMapNodeBoxOption {

    //选项值编号
    private Integer valueTextNumber;

    //盒子列表
    private List<BoxMapNodeBox> boxList;

    /**
     * 以下为 get set 方法
     */

    public Integer getValueTextNumber() {
        return valueTextNumber;
    }

    public void setValueTextNumber(Integer valueTextNumber) {
        this.valueTextNumber = valueTextNumber;
    }

    public List<BoxMapNodeBox> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<BoxMapNodeBox> boxList) {
        this.boxList = boxList;
    }

}