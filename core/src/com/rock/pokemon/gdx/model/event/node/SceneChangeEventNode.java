package com.rock.pokemon.gdx.model.event.node;

import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import lombok.Getter;
import lombok.Setter;

/**
 * 场景切换 事件节点
 *
 * @Author ayl
 * @Date 2024-04-20
 */
@Getter
@Setter
public class SceneChangeEventNode implements EventNodeTemplate {

    //对应场景地图配置地址
    private String targetWorldMapConfigPath;

    //目标坐标
    private Integer targetX;

    //目标坐标
    private Integer targetY;

    @Override
    public EventNodeTypeEnum getType() {
        //对应枚举类型
        return EventNodeTypeEnum.SCENE_CHANGE;
    }

}