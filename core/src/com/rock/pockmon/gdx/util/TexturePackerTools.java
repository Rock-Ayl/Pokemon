package com.rock.pockmon.gdx.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * 资源打包器
 *
 * @Author ayl
 * @Date 2022-10-17
 */
public class TexturePackerTools {

    public static void main(String[] args) {

        //将路比走路图片打包至对应目录
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/walk/",
                "assets/packed/image/people/ruby/walk/",
                "textures");

        //将路比走路图片打包至对应目录
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/stand/",
                "assets/packed/image/people/ruby/stand/",
                "textures");

    }

}
