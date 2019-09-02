package com.pop.image.ui;

import com.pop.image.entity.Good;
import com.pop.image.event.ImageButton;
import com.pop.image.utils.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * @program: Common-Utils
 * @description: 图片合成工具的ui
 * @author: 范凌轩
 * @create: 2019-05-24 16:34
 **/
public class ImageUI {

    /**
     * 启动方法
     */
    public void start() {
        initUI();//初始化UI界面
    }


    private void initUI() {

        JFrame frame = new JFrame("图片合成工具");
        SwingConsole.run(frame,600,500);
        Good good = new Good();



        createCol(frame, good);
        createCol(frame, good);
        createCol(frame, good);
    }

    private void createCol(JFrame frame, Good good) {
        JTextField jTextField = new JTextField(30);
        JLabel label = new JLabel("请选择底图");
        frame.add(label);

        jTextField.setHorizontalAlignment(JTextField.LEFT);
//        jTextField.setEditable(false);
        jTextField.setEnabled(false);
        frame.add(jTextField);
        JButton button = new JButton("添加");
        button.addActionListener(new ImageButton(good,jTextField ));
        frame.add(button);
    }

}