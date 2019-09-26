package com.pop.image;

import com.pop.image.ui.ImageUI;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: Common-Utils
 * @description: 合成工具图片的启动类
 * @author: 范凌轩
 * @create: 2019-05-24 16:33
 **/
public class BootStrap {

    public static void main(String[] args) throws IOException {
//        new ImageUI().start();
        merge();
    }

    static  File[] files = new File[3];
    static {

        files[0] = new File("d:/baoyou.png");
        files[1] = new File("d:/head.png");
        files[2] = new File("d:/icon.png");

    }
    private static void merge() throws IOException {

        int count = files.length;
        Image[] images = new Image[count];
        for (int i = 0; i <files.length ; i++) {
            images[i]=ImageIO.read(files[i]);
        }

        BufferedImage imagebuff = new BufferedImage(500,600,
                BufferedImage.TYPE_INT_RGB);

        FileOutputStream
                out  = new FileOutputStream("d:/test.png");

        Graphics g = imagebuff.createGraphics();
        for (int i = 0; i <count ; i++) {
            g.drawImage(images[i],0,0,
                    images[i].getWidth(null),
                    images[i].getHeight(null),null);
        }
        g.dispose();
        JPEGImageEncoder encoder
                 = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(imagebuff);
        out.close();

    }



}
