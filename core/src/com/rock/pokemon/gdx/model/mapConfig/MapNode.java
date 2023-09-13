package com.rock.pokemon.gdx.model.mapConfig;

/**
 * 世界节点配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
public class MapNode {

    //节点操作
    private String operate;

    //节点备注
    private String remark;

    //资源路径
    private String filePath;

    //资源区域名
    private String regionName;

    //资源坐标
    private Integer x;
    private Integer y;

    //资源宽高
    private Float width;
    private Float height;

    //是否可以行走
    private Boolean walkable;

    /**
     * 以下为 get set
     */

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

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

}