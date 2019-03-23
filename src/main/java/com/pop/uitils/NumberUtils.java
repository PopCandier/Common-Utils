package com.pop.uitils;

/**
 * @author Pop
 * @date 2019/3/23 12:09
 */
public class NumberUtils {

    /**
     * 判断一个数学数组是否为空，如果有一个为空，那么返回false
     * @param numbers
     * @return
     */
    public static boolean checkNumbersIsNotNull(Number... numbers){
        boolean flag = true;
        for(Number number:numbers){ if((number!=null?true:false)==false){flag=false;break;}}
        return flag;
    }
    public static boolean checkNumberIsNotNull(Number number){ return number!=null?true:false; }
    public static boolean checkNumberIsNull(Number number){ return number==null?true:false; }



}
