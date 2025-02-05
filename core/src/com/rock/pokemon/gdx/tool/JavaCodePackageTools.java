package com.rock.pokemon.gdx.tool;

import com.rock.pokemon.gdx.util.FileExtraUtils;

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
    private static final String PATH = "/Users/ayl/work/Pokemon/core";
    //输出后的文件
    private static final String OUT_FILE = "/Users/ayl/Downloads/out.java";

    public static void main(String[] args) {

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
                .collect(Collectors.toList());

        /**
         * step 2. 读取所有内容并拼接
         */

        //todo

        /**
         * step 3. 输出到文件
         */

    }

}