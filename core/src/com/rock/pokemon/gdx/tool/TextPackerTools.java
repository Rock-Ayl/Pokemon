package com.rock.pokemon.gdx.tool;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文本打包器,将所有文本目录下的文本读取到内存中,去重,方便制作fnt
 *
 * @Author ayl
 * @Date 2022-11-03
 */
public class TextPackerTools {

    public static void main(String[] args) {
        //文本目录
        File dir = new File("assets/text");
        //收集该目录及子目录下的所有文件
        List<File> fileList = collectFiles(dir);
        //只保留txt文件
        fileList = fileList.stream()
                .filter(p -> "txt".equalsIgnoreCase(FilenameUtils.getExtension(p.getName())))
                .collect(Collectors.toList());

        System.out.println();
    }

    /**
     * 收集目录里所有的文件
     *
     * @param dir 文件目录
     * @return
     */
    private static List<File> collectFiles(File dir) {
        //判空
        if (dir == null || dir.isFile()) {
            //过
            return new ArrayList<>();
        }
        //初始化文件列表
        List<File> result = new ArrayList<>();
        //循环
        for (File file : dir.listFiles()) {
            //如果是文件
            if (file.isFile()) {
                //直接组装
                result.add(file);
                //本轮过
                continue;
            }
            //如果是目录
            if (file.isDirectory()) {
                //获取里面的文件并组装
                result.addAll(collectFiles(file));
                //本轮过
                continue;
            }
        }
        //返回
        return result;
    }

}
