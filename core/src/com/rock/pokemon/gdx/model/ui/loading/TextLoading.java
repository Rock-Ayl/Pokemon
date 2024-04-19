package com.rock.pokemon.gdx.model.ui.loading;

import com.badlogic.gdx.Gdx;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文本载入
 */
public class TextLoading {

    /**
     * 载入游戏全部文本
     *
     * @return
     */
    public static Map<Integer, String> initText() {
        //初始化结果
        Map<Integer, String> result = new HashMap<>();
        try {
            //根据当前语言,获取对应语言文本
            File file = new File(String.format(FilePaths.SYSTEM_CONFIG_TEXT_FILE_PATH, Settings.LANG.getPath()));
            //读取行列表
            List<String> stringList = FileUtils.readLines(file, "UTF-8");
            //循环
            for (String sentence : stringList) {
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
