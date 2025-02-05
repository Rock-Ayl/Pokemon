package com.rock.pokemon.gdx.tool;

import com.rock.pokemon.gdx.util.FileExtraUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java 代码打包器,将 路径下 所有代码打包成一个文件（尝试 喂AI 问思路）
 *
 * @Author ayl
 * @Date 2025-02-05
 */
public class JavaCodePackageTools {

    //路径
    private static final String PATH = "/Users/ayl/work/Pokemon";
    //输出后的文件
    private static final String OUT_FILE = "/Users/ayl/Downloads/out.java";

    public static void main(String[] args) throws Exception {

        /**
         * step 1. 收集所有 .java 文件
         */

        //收集所有 .java 文件
        List<String> javaFilePathList = FileExtraUtils
                //收集所有文件
                .collectFile(PATH)
                .stream()
                //仅保留 .java 后缀的
                .filter(p -> p.endsWith(".java"))
                //排序
                .sorted()
                .collect(Collectors.toList());

        /**
         * step 2. 读取所有内容并拼接
         */

        //内容
        StringBuilder str = new StringBuilder();
        //循环
        for (String javaFilePath : javaFilePathList) {
            //解析文件内容
            String fileContent = FileUtils.readFileToString(new File(javaFilePath), "UTF-8");
            //组装、换行
            str.append(fileContent).append("\n").append("\n");
        }

        /**
         * step 3. 输出到文件
         */

        //写入文件
        FileUtils.writeStringToFile(new File(OUT_FILE), str.toString());
        System.out.println("===写入成功===");

    }

}