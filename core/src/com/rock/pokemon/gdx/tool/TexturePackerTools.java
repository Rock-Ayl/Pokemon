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

        //路比 走路、站立、跑
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/walk/",
                "assets/packed/image/people/ruby/walk/",
                "textures");
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/stand/",
                "assets/packed/image/people/ruby/stand/",
                "textures");
        TexturePacker.process(
                "assets/unpacked/image/people/ruby/run/",
                "assets/packed/image/people/ruby/run/",
                "textures");

        //小田卷博士 走路、站立、跑
        TexturePacker.process(
                "assets/unpacked/image/people/prof_birch/walk/",
                "assets/packed/image/people/prof_birch/walk/",
                "textures");
        TexturePacker.process(
                "assets/unpacked/image/people/prof_birch/stand/",
                "assets/packed/image/people/prof_birch/stand/",
                "textures");

        //草
        TexturePacker.process(
                "assets/unpacked/image/map/grass/",
                "assets/packed/image/map/grass/",
                "textures");

        //花
        TexturePacker.process(
                "assets/unpacked/image/map/flower/",
                "assets/packed/image/map/flower/",
                "textures");

        //事物
        TexturePacker.process(
                "assets/unpacked/image/map/object/",
                "assets/packed/image/map/object/",
                "textures");

        //各种房子
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
