package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * 事物节点配置类实体
 *
 * @Author ayl
 * @Date 2023-09-15
 */
public class WorldObjectMapNode {

    //节点备注
    private String remark;

    //动画帧持续时间(如果有,则视为动画,否则视为静态图片)
    private Float frameDuration;

    //资源路径
    private String filePath;

    //资源区域名
    private String regionName;

    //事物宽高
    private Float width;
    private Float height;

    //事物是否可以行走
    private Boolean walkable;

    //指定 可以 行走的坐标列表
    private List<Location> tileList;

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

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Boolean getWalkable() {
        return walkable;
    }

    public void setWalkable(Boolean walkable) {
        this.walkable = walkable;
    }

    public List<Location> getTileList() {
        return tileList;
    }

    public void setTileList(List<Location> tileList) {
        this.tileList = tileList;
    }

    public Float getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(Float frameDuration) {
        this.frameDuration = frameDuration;
    }

}