package com.pop.uitils;

/**
 * @author Pop
 * @date 2019/3/23 12:15
 */

import java.util.Arrays;

/**
 * 数组工具类
 */
public class ArraysUtils<T> {

    /**
     * 生成一个数字有关的数组
     * @param params
     * @return
     */
    public  static Number[]  generateNumberArray(Number ... params){
        int len = params.length;
        Number[] numbers = new Number[len];
        for(int i=0;i<len;i++) {
            Arrays.fill(numbers, i, i+1, params[i]);
        }
        return numbers;
    }


}
