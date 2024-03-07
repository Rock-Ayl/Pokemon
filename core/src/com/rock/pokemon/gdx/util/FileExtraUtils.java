package com.rock.pokemon.gdx.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件 额外工具包
 *
 * @Author ayl
 * @Date 2023-11-20
 */
public class FileExtraUtils {

    /**
     * 收集该路径下所有文件
     *
     * @param dirPath 文件夹目录
     * @return
     */
    public static List<String> collectFile(String dirPath) {
        //实现
        return collectFile(dirPath, null);
    }

    /**
     * 收集该路径下所有文件,指定包含文件名的文件
     *
     * @param dirPath  文件夹目录
     * @param fileName 包含指定文件名,选填
     * @return
     */
    public static List<String> collectFile(String dirPath, String fileName) {
        //判空
        if (StringUtils.isBlank(dirPath)) {
            //过
            return new ArrayList<>();
        }
        //初始化结果列表
        List<String> fileList = new ArrayList<>();
        //初始化目录
        File directory = new File(dirPath);
        //如果不是目录
        if (directory.exists() == false || directory.isDirectory() == false) {
            //过
            return fileList;
        }
        //获取目录下的所有文件和子目录
        File[] fileArr = directory.listFiles();
        //如果没有文件
        if (fileArr == null || fileArr.length < 1) {
            //过
            return fileList;
        }
        //遍历文件和子目录
        for (File file : fileArr) {
            //如果是目录
            if (file.isDirectory()) {
                //递归
                fileList.addAll(collectFile(file.getPath(), fileName));
                //本轮过
                continue;
            }
            //如果不是文件
            if (file.isFile() == false) {
                //本轮过
                continue;
            }
            //如果 不指定文件名 or 是目标文件
            if (fileName == null || fileName.equals(file.getName())) {
                //加入结果
                fileList.add(file.getPath());
            }
        }
        //返回
        return fileList;
    }

}
