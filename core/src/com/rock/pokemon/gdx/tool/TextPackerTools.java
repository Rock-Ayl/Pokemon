package com.rock.pokemon.gdx.tool;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 汉字文本打包器,将所有文本目录下的文本读取到内存中,去重,方便制作fnt
 *
 * @Author ayl
 * @Date 2022-11-03
 */
public class TextPackerTools {

    //文本打包目录
    private static final String TEXT_PATH = "assets/text/";
    //输出文件名
    private static final String OUT_FILE_NAME = "out.txt";

    public static void main(String[] args) throws IOException {
        //文本目录
        File dir = new File(TEXT_PATH);
        //收集该目录及子目录下的所有文件
        List<File> fileList = collectFiles(dir);
        //只保留txt文件
        fileList = fileList.stream()
                //获取文件后缀并过滤
                .filter(p -> "txt".equalsIgnoreCase(FilenameUtils.getExtension(p.getName())))
                //转化为列表
                .collect(Collectors.toList());
        //字符集合
        Set<Character> set = new HashSet<>();
        //循环文本
        for (File file : fileList) {
            //解析文件内容
            String str = FileUtils.readFileToString(file, "UTF-8");
            //循环字符
            for (char c : str.toCharArray()) {
                //转化为string
                String letter = String.valueOf(c);
                //判空
                if (StringUtils.isBlank(letter)) {
                    //本轮过
                    continue;
                }
                //如果不是汉字
                if (validateName(letter) == false) {
                    //本轮过
                    continue;
                }
                //组装
                set.add(c);
            }
        }
        //初始化结果集
        StringBuilder str = new StringBuilder();
        //排序、循环
        for (Character character : set.stream().sorted().collect(Collectors.toList())) {
            //组装至结果集
            str.append(character);
        }
        //初始化输出文件
        File outFile = new File(TEXT_PATH + OUT_FILE_NAME);
        //如果存在
        if (outFile.exists()) {
            //删除
            FileUtils.deleteQuietly(outFile);
        }
        //写入文件
        FileUtils.writeStringToFile(outFile, str.toString(), "UTF-8");
        //结束
        System.out.println(String.format(" *************** finished  count=[%s] ******************", str.length()));
    }

    /**
     * 验证是否是汉字
     *
     * @param value
     * @return
     */
    private static Boolean validateName(String value) {
        //正则判断
        return value.matches("[\u4e00-\u9fa5]+");
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
        //获取文件列表
        File[] fileArr = dir.listFiles();
        //如果没有文件
        if (fileArr == null || fileArr.length < 1) {
            //过
            return new ArrayList<>();
        }
        //初始化文件列表
        List<File> result = new ArrayList<>();
        //循环
        for (File file : fileArr) {
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
