package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * Box节点盒子 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
public class BoxMapNodeBoxOption {

    //选项值
    private String value;

    //盒子列表
    private List<BoxMapNodeBox> boxList;

    /**
     * 以下为 get set 方法
     */

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<BoxMapNodeBox> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<BoxMapNodeBox> boxList) {
        this.boxList = boxList;
    }
    
}