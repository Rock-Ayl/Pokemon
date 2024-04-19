package com.rock.pokemon.gdx.model.map.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Box 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
@Getter
@Setter
public class BoxMapConfig {

    //配置名称
    private String name;

    //BoxMap
    private Map<String, BoxMapNode> boxMap;

    /**
     * Box节点 配置类实体
     */
    @Getter
    @Setter
    public static class BoxMapNode {

        //备注
        private String remark;

        //盒子列表
        private List<BoxMapNodeBox> boxList;

    }

    @Getter
    @Setter
    public static class BoxMapNodeBox {

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

    /**
     * Box节点盒子 配置类实体
     *
     * @Author ayl
     * @Date 2023-11-22
     */
    @Getter
    @Setter
    public static class BoxMapNodeBoxOption {

        //选项值编号
        private Integer valueTextNumber;

        //盒子列表
        private List<BoxMapNodeBox> boxList;

    }

}