package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * Box节点盒子 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
public class BoxMapNodeBox {

    //盒子节点类型
    private String type;

    /**
     * OptionBox 独有参数
     */

    //可选项列表
    private List<String> optionList;

    /**
     * DialogueBox 独有参数
     */

    //文本编号
    private Integer textNumber;

    /**
     * 以下为 get set 方法
     */

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }

    public Integer getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(Integer textNumber) {
        this.textNumber = textNumber;
    }

}