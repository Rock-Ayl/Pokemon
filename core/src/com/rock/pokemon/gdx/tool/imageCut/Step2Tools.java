package com.rock.pokemon.gdx.tool.imageCut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 切割[行走图原件]步骤2,根据步骤1,分析出对应所需行走图,裁剪出指定图片
 */
public class Step2Tools {

    //要切割的文件路径(必须要存在具体文件)
    private static final String IMAGE_PATH = "/Users/ayl/Downloads/去底.png";

    //输出路径(必须要存在具体文件夹)
    private static final String OUT_PATH = "/Users/ayl/Downloads/out/";

    //行走图固定宽高
    private static final int SMALL_WIDTH = 16;
    private static final int SMALL_HEIGHT = 24;

    //整体剪裁偏移像素
    private static final int X_OFFSET = 1;
    private static final int Y_OFFSET = -5;

    //每张单独的偏倚量
    private static final List<Integer> ONE_X_OFFSET_LIST = Arrays.asList(0, 2, 6, 7, 8, 10, 12, 13, 13);

    public static void main(String[] args) throws Exception {
        //读取原始图片
        BufferedImage originalImage = ImageIO.read(new File(IMAGE_PATH));
        //循环2
        for (int x = 0; x < 9; x++) {
            //切
            BufferedImage subImage = originalImage.getSubimage(X_OFFSET + x * SMALL_WIDTH + ONE_X_OFFSET_LIST.get(x), Y_OFFSET + 18 * SMALL_HEIGHT, SMALL_WIDTH, SMALL_HEIGHT);
            //初始化对应文件
            File outputImageFile = new File(OUT_PATH + "output_" + x + "_" + 18 + ".png");
            //写入
            ImageIO.write(subImage, "png", outputImageFile);
        }
        System.out.println("结束");
    }

}
