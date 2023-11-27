package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

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

    //站立
    private String standFilePath;

    //走
    private String walkFilePath;

    //跑步
    private String runFilePath;

    //事件列表
    private List<NpcMapNodeEvent> eventList;

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

    public String getStandFilePath() {
        return standFilePath;
    }

    public void setStandFilePath(String standFilePath) {
        this.standFilePath = standFilePath;
    }

    public String getWalkFilePath() {
        return walkFilePath;
    }

    public void setWalkFilePath(String walkFilePath) {
        this.walkFilePath = walkFilePath;
    }

    public String getRunFilePath() {
        return runFilePath;
    }

    public void setRunFilePath(String runFilePath) {
        this.runFilePath = runFilePath;
    }

    public List<NpcMapNodeEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<NpcMapNodeEvent> eventList) {
        this.eventList = eventList;
    }

}