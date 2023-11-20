package com.rock.pokemon.gdx.model.mapConfig;

/**
 * Npc 节点配置类实体
 *
 * @Author ayl
 * @Date 2023-11-20
 */
public class NpcMapNode {

    //节点备注
    private String remark;

    //资源区域名
    private String regionName;

    /**
     * 以下为 get set 方法
     */

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}