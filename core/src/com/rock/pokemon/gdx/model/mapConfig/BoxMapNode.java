package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Box节点 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
@Getter
@Setter
public class BoxMapNode {

    //备注
    private String remark;

    //盒子列表
    private List<BoxMapNodeBox> boxList;

}