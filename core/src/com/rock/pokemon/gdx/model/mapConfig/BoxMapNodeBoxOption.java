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
public class BoxMapNodeBoxOption {

    //选项值编号
    private Integer valueTextNumber;

    //盒子列表
    private List<BoxMapNodeBox> boxList;

}