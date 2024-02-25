package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Box节点盒子 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
@Getter
@Setter
public class BoxMapNodeBox {

    //盒子节点类型
    private String type;

    /**
     * OptionBox 独有参数
     */

    //可选项对象列表
    private List<BoxMapNodeBoxOption> optionList;

    /**
     * DialogueBox 独有参数
     */

    //文本编号
    private Integer textNumber;

}