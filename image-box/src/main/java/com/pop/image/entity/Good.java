package com.pop.image.entity;

import java.io.File;

/**
 * @program: Common-Utils
 * @description: 商品
 * @author: 范凌轩
 * @create: 2019-05-24 16:22
 * 记录所生成图片信息信息
 **/
public class Good {

    private File mainImage;//商品主图
    private File foodIcon;//商品的icon
    private File priceBg;//商品价格的底图

    private String title;//商品名称
    private String  introduce;//商品的介绍
    private String exemption;//商品邮寄

    public Good() {
    }

    public Good(File mainImage, File foodIcon, File priceBg) {
        this.mainImage = mainImage;
        this.foodIcon = foodIcon;
        this.priceBg = priceBg;
    }

    public File getMainImage() {
        return mainImage;
    }

    public void setMainImage(File mainImage) {
        this.mainImage = mainImage;
    }

    public File getFoodIcon() {
        return foodIcon;
    }

    public void setFoodIcon(File foodIcon) {
        this.foodIcon = foodIcon;
    }

    public File getPriceBg() {
        return priceBg;
    }

    public void setPriceBg(File priceBg) {
        this.priceBg = priceBg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getExemption() {
        return exemption;
    }

    public void setExemption(String exemption) {
        this.exemption = exemption;
    }
}
