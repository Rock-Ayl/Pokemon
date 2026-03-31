package com.rock.pokemon.gdx.enums;

import com.rock.pokemon.gdx.common.FilePaths;
import lombok.Getter;

/**
 * 文件 枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public enum FileEnum {

    /**
     * 非通用 世界配置
     */

    MAP_CONFIG_PATH_OF_LITTLE_ROOT("file_0001001", FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT),
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST("file_0001002", FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST),
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND("file_0001003", FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND),

    /**
     * todo 其他的也放进来
     */

    ;

    //唯一编码
    private String code;
    //路径
    private String path;

    FileEnum(String code, String path) {
        this.code = code;
        this.path = path;
    }

}