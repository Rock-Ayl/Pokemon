package com.rock.pokemon.gdx.tool.imageCut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 切割[行走图原件]步骤1,将图片分片,借此推测出所需行走图 大概坐标
 */
public class Step1Tools {

    //要切割的文件路径(必须要存在具体文件)
    private static final String IMAGE_PATH = "/Users/ayl/Downloads/去底.png";

    //输出路径(必须要存在具体文件夹)
    private static final String OUT_PATH = "/Users/ayl/Downloads/out/";

    //行走图固定宽高
    private static final int SMALL_WIDTH = 16;
    private static final int SMALL_HEIGHT = 24;

    public static void main(String[] args) throws Exception {
        //读取原始图片
        BufferedImage originalImage = ImageIO.read(new File(IMAGE_PATH));
        //计算横纵切割多少块
        int cols = originalImage.getWidth() / SMALL_WIDTH;
        int rows = originalImage.getHeight() / SMALL_HEIGHT;
        //循环2
        for (int x = 0; x < cols; x++) {
            //循环1
            for (int y = 0; y < rows; y++) {
                //切
                BufferedImage subImage = originalImage.getSubimage(x * SMALL_WIDTH, y * SMALL_HEIGHT, SMALL_WIDTH, SMALL_HEIGHT);
                //初始化对应文件
                File outputImageFile = new File(OUT_PATH + "output_" + x + "_" + y + ".png");
                //写入
                ImageIO.write(subImage, "png", outputImageFile);
            }
        }
        System.out.println("结束");
    }

}
