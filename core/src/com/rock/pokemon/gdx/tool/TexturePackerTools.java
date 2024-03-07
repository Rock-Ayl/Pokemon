package com.rock.pokemon.gdx.tool;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.rock.pokemon.gdx.util.FileExtraUtils;
import org.apache.commons.io.FilenameUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 资源打包器
 *
 * @Author ayl
 * @Date 2022-10-17
 */
public class TexturePackerTools {

    /**
     * 统一将图片、动画帧打包至对应目录
     *
     * @param args
     */
    public static void main(String[] args) {
        //未打包文件路径
        String input = "assets/unpacked/";
        //已打包文件路径
        String output = "assets/packed/";
        //收集并过滤文件,按照文件夹分组
        Map<String, List<String>> pathGroupMap = FileExtraUtils.collectFile(input)
                .stream()
                //目前只有png的素材需要打包
                .filter(p -> "png".equalsIgnoreCase(FilenameUtils.getExtension(p)))
                //按照文件夹分组
                .collect(Collectors.groupingBy(FilenameUtils::getPath));
        //循环所有文件夹
        for (String inputPath : pathGroupMap.keySet()) {
            //生成对应打包路径
            String outputPath = inputPath.replaceFirst(input, output);
            //打包该路径下所有资源
            TexturePacker.process(inputPath, outputPath, "textures");
        }

    }

}
