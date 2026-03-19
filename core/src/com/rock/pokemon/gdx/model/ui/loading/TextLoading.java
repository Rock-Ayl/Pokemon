package com.rock.pokemon.gdx.model.ui.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 文本载入
 */
public class TextLoading {

    /**
     * 载入游戏全部文本
     *
     * @param languageEnum 指定对应语言枚举
     * @return
     */
    public static Map<Integer, String> initText(Settings.LanguageEnum languageEnum) {
        //初始化结果
        Map<Integer, String> result = new HashMap<>();
        try {
            //根据当前语言,获取对应语言文本路径
            String filePath = String.format(FilePaths.SYSTEM_CONFIG_TEXT_FILE_PATH, languageEnum.getPath());
            //使用 LibGDX 的文件句柄读取内部资源 (兼容 Desktop 和 Android)
            FileHandle fileHandle = Gdx.files.internal(filePath);
            //读取文件内容为字符串并按行分割
            String content = fileHandle.readString("UTF-8");
            String[] lines = content.split("\\r?\\n");
            
            //循环
            for (String sentence : lines) {
                //判空
                if (StringUtils.isBlank(sentence)) {
                    //本轮过
                    continue;
                }
                //根据=切割
                String[] arr = sentence.split("=");
                //如果不符合规则
                if (arr.length != 2) {
                    //本轮过
                    continue;
                }
                //获取文本并替换里面的换行
                String text = arr[1].replaceAll("\\\\n", "\n");
                //组装编号及文本
                result.put(Integer.valueOf(arr[0]), text);
            }
        } catch (Exception e) {
            //日志
            Gdx.app.error("LoadingError", "载入文本失败", e);
        }
        //返回
        return result;
    }

}
