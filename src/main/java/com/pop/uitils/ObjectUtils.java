package com.pop.uitils;

/**
 * @author Pop
 * @date 2019/3/23 12:55
 */
public class ObjectUtils {

    public static boolean checkObjcetIsNotNull(Object obj){return obj!=null?true:false;}
    public static boolean checkObjcetIsNull(Object obj){return obj==null?true:false;}

    /**
     * 如果对象数组有一个为null，返回false;
     * @param objs
     * @return
     */
    public static boolean checkObjectsIsNotNull(Object... objs){
        boolean flag = true;
        for(Object number:objs){ if((objs!=null?true:false)==false){flag=false;break;}}
        return flag;
    }

}
