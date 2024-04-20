package com.rock.pokemon.gdx.model.event.node;

import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import lombok.Getter;
import lombok.Setter;

/**
 * 播放音效 事件节点
 *
 * @Author ayl
 * @Date 2024-04-20
 */
@Getter
@Setter
public class MusicSoundPlayEventNode implements EventNodeTemplate {

    //音乐编号
    private Integer soundId;

    @Override
    public EventNodeTypeEnum getType() {
        //对应枚举类型
        return EventNodeTypeEnum.MUSIC_SOUND_PLAY;
    }

}