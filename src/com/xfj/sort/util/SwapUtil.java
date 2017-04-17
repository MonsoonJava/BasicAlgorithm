package com.xfj.sort.util;

/**
 * Created by asus on 2017/4/14.
 */
public class SwapUtil {

    public static <T> void  swap(T[] arr,int i,int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
