package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * 世界节点配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
public class WorldMapNode {

    //节点备注
    private String remark;

    //资源路径
    private String filePath;

    //资源区域名
    private String regionName;

    //事物名称
    private String worldObjectName;

    //npc名称
    private String npcName;

    //坐标列表
    private List<Location> locationList;

    /**
     * 坐标对象
     */
    public static class Location {

        //资源坐标
        private Integer x;
        private Integer y;

        /**
         * 以下为 get set
         */

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

    }

    /**
     * 以下为 get set
     */

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public String getWorldObjectName() {
        return worldObjectName;
    }

    public void setWorldObjectName(String worldObjectName) {
        this.worldObjectName = worldObjectName;
    }

    public String getNpcName() {
        return npcName;
    }

    public void setNpcName(String npcName) {
        this.npcName = npcName;
    }

}