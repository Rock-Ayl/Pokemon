package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * 事物配置类实体
 *
 * @Author ayl
 * @Date 2023-09-15
 */
public class WorldObjectMapConfig {

    //配置名称
    private String name;

    //事物列表
    private List<WorldObjectMapNode> worldObjectList;

    /**
     * 以下为 get set
     *
     * @return
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorldObjectMapNode> getWorldObjectList() {
        return worldObjectList;
    }

    public void setWorldObjectList(List<WorldObjectMapNode> worldObjectList) {
        this.worldObjectList = worldObjectList;
    }

}