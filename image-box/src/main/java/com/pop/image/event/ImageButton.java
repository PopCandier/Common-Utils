package com.pop.image.event;

import com.pop.image.entity.Good;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @program: Common-Utils
 * @description: 图片的按钮
 * @author: 范凌轩
 * @create: 2019-05-24 17:30
 **/
public class ImageButton  implements ActionListener {

    private Good good;
    private JTextField textField;
    public ImageButton(Good good,JTextField textField) {
        this.good = good;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser=new JFileChooser();
        //只允许筛选文件，只允许选择图片
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showDialog(new JLabel("测试"), "选择");
        File file = chooser.getSelectedFile();
        textField.setText(file.getAbsolutePath());
        good.setPriceBg(file);

    }
}
