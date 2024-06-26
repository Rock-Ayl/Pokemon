package com.rock.pokemon.gdx.model.manager;

import com.rock.pokemon.gdx.enums.GameUserActionStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 存档管理器(存档文件实体),记录了当前存档的所有内容,通过 序列化/反序列化,读取/存储 存档
 *
 * @Author ayl
 * @Date 2024-3-5
 */
@Getter
@Setter
public class SaveManager {

    //todo 主角 npcId ,目前固定写死
    private String adventurerNpcId = "npc_id_1";

    //todo 主角 npc 配置名称,目前固定写死
    private String adventurerNpcMapConfigName = "ruby_1";

    /**
     * 当前用户行动状态 枚举 {@link GameUserActionStatusEnum} ,默认 normal
     */
    private GameUserActionStatusEnum gameUserActionStatusEnum = GameUserActionStatusEnum.NORMAL;

}