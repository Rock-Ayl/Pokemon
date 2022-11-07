package com.rock.pokemon.gdx.ui.loading;

import com.badlogic.gdx.Gdx;
import com.rock.pokemon.gdx.enums.LanguageEnum;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文本载入
 */
public class TextLoading {

    /**
     * 根据语言枚举,载入全部文本
     *
     * @param languageEnum 语言枚举
     * @return
     */
    public static Map<Integer, String> initText(LanguageEnum languageEnum) {
        //初始化结果
        Map<Integer, String> result = new HashMap<>();
        //获取对应语言文本
        File file = new File("assets/text/" + languageEnum.getPath() + "/Text.txt");
        try {
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
        } catch (IOException e) {
            //日志
            Gdx.app.error("LoadingError", "载入文本失败", e);
            //直接返回
            return result;
        }
        //返回
        return result;
    }

}
