package com.pop.uitils;


/**
 * @author Pop
 * @date 2019/4/23 20:09
 */
public class StringUtils {

    public static Boolean isNotEmpty(String src){ return src!=null&&src!=""?true:false; }
    public static Boolean isEmpty(String src){return src==null||src==""?true:false;}

}
