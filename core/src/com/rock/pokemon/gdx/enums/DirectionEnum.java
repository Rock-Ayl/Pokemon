package com.rock.pokemon.gdx.enums;

import com.rock.pokemon.gdx.common.Settings;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 人物方向 {@link com.rock.pokemon.gdx.model.map.Person} 枚举
 */
@Getter
public enum DirectionEnum {

    NORTH(0, 1, Settings.INPUT_KEY_UP, "north", "北"),
    EAST(1, 0, Settings.INPUT_KEY_RIGHT, "east", "东"),
    SOUTH(0, -1, Settings.INPUT_KEY_DOWN, "south", "南"),
    WEST(-1, 0, Settings.INPUT_KEY_LEFT, "west", "西"),

    ;

    //该方向每次移动坐标值
    private int dx;
    private int dy;

    //该方向对应的输入按键编码(暂时用枚举)
    private int keycode;

    //名字
    private String name;
    //中文名
    private String zhName;

    DirectionEnum(int dx, int dy, int keycode, String name, String zhName) {
        this.dx = dx;
        this.dy = dy;
        this.keycode = keycode;
        this.name = name;
        this.zhName = zhName;
    }

    /**
     * 根据方向名字,解析方向枚举
     *
     * @param name 名字
     * @return
     */
    public static DirectionEnum parseByName(String name) {
        //判空
        if (StringUtils.isNotBlank(name)) {
            //循环
            for (DirectionEnum value : DirectionEnum.values()) {
                //如果对应上
                if (value.name.equals(name)) {
                    //返回
                    return value;
                }
            }
        }
        //默认,南
        return SOUTH;
    }

    /**
     * 根据方向输入,解析方向枚举
     *
     * @param keycode 案件编码
     * @return
     */
    public static DirectionEnum parseByKeycode(int keycode) {
        //循环
        for (DirectionEnum value : DirectionEnum.values()) {
            //如果对应上
            if (value.keycode == keycode) {
                //返回
                return value;
            }
        }
        //默认,南
        return SOUTH;
    }

}