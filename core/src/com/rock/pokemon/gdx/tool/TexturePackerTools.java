package com.rock.pokemon.gdx.tool;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * 资源打包器
 *
 * @Author ayl
 * @Date 2022-10-17
 */
public class TexturePackerTools {

    /**
     * 将图片、动画帧打包至对应目录
     *
     * @param args
     */
    public static void main(String[] args) {

        //路比走路
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/walk/",
                "assets/packed/image/people/ruby/walk/",
                "textures");

        //路比站立
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/stand/",
                "assets/packed/image/people/ruby/stand/",
                "textures");

        //草
        TexturePacker.process(
                "assets/unpacked/image/map/grass/",
                "assets/packed/image/map/grass/",
                "textures");

        //事物
        TexturePacker.process(
                "assets/unpacked/image/map/object/",
                "assets/packed/image/map/object/",
                "textures");

        //两位主角的家
        TexturePacker.process(
                "assets/unpacked/image/map/house/",
                "assets/packed/image/map/house/",
                "textures");

        //ui
        TexturePacker.process(
                "assets/unpacked/image/ui/",
                "assets/packed/image/ui/",
                "textures");

    }

}
