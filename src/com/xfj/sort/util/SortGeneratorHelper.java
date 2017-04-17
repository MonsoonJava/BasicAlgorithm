package com.xfj.sort.util;

import com.xfj.sort.interfac.Sort;

import java.util.Random;

/**
 * Created by asus on 2017/4/14.
 */
public class SortGeneratorHelper {

    //生成用于测试的数组
    public static Integer[] generatorIntegerArray(int n,int rangeL,int rangeH){
        Integer[] arr = new Integer[n];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0;i < n;i++){
            arr[i] = random.nextInt(rangeH - rangeL) + rangeL;
        }
        return arr;
    }

    //打印数组
    public  static <T> void printArray(T[] arr){
        if(null == arr || arr.length == 0 ) return;
        for(T t : arr){
            System.out.print(t + " ");
        }
    }

    public static <T extends Comparable> boolean isSorted(T[] arr,int n){
        if(null == arr || arr.length <= 1 ) return true;
        for (int i = 0; i < n - 1 ;i++){
            if(arr[i].compareTo(arr[i + 1]) > 0)
                return false;
        }
        return true;
    }

    public static <T extends Comparable> void testSort(String sortName, Sort sort,T[] arr,int n){
        long startTime = System.currentTimeMillis();
        sort.sort(arr,n);
        long endTime = System.currentTimeMillis();
        assert (isSorted(arr,n));
        long costTime = endTime - startTime;
        System.out.println(sortName + "cost time is " + costTime);
    }




}
