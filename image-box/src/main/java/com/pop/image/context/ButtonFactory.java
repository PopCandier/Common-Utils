package com.pop.image.context;

import javax.swing.*;

/**
 * @program: Common-Utils
 * @description: 生产按钮的工厂
 * @author: 范凌轩
 * @create: 2019-05-24 18:29
 **/
public class ButtonFactory {
    public static JButton create(){return new JButton();}
    public static JButton create(String buttonName){return new JButton(buttonName);}
}
