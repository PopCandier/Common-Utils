package com.pop.image.utils;

import javax.swing.*;
import java.awt.*;

/**
 * @program: Common-Utils
 * @description: 一个简单的工具类
 * @author: 范凌轩
 * @create: 2019-05-24 16:55
 **/
public class SwingConsole {
    public static void run(final JFrame frame,final int width,
                           final int height){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width,height);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLayout(new GridLayout(3,3));
            }
        });
    }
}
