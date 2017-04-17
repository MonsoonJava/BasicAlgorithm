package com.xfj.sort.quick;

import com.xfj.sort.interfac.Sort;
import com.xfj.sort.util.SortGeneratorHelper;
import com.xfj.sort.util.SwapUtil;

/**
 * Created by asus on 2017/4/15.
 */
public class QuickSort implements Sort{
    @Override
    public <T extends Comparable> void sort(T[] arr, int n) {
        //对闭区间[0,n-1]进行排序
        QuickSort.quickS(arr,0,n-1);
    }
    //对闭区间[0,n-1]进行排序
    public static <T extends Comparable> void quickS(T[] arr,int lo,int hi){
        //递归退出条件
        if(lo >= hi) return;
        int j = QuickSort.partition(arr, lo, hi);
        QuickSort.quickS(arr,lo,j-1);
        QuickSort.quickS(arr,j+1,hi);
    }

    private static <T extends Comparable> int partition(T[] arr,int lo,int hi){
        int i = lo;
        int j = hi+1;
        while (true){
            //一直循环 直到找到第一个大于arr[lo]的i的位置
            while (less(arr[++i],arr[lo]))
                if(i == hi) break;
            //一直循环 直到找到第一个小于arr[lo]的j的位置
            while (less(arr[lo],arr[--j]))
                if( j == lo) break;
            if(i >= j) break;
            SwapUtil.swap(arr,i,j);
        }
        SwapUtil.swap(arr,lo,j);
        return j;
    }

    private static <T extends Comparable> boolean less(T a,T b){
        return a.compareTo(b) <= 0 ? true : false;
    }

    public static void main(String[] args){
        Integer[] arr = SortGeneratorHelper.generatorIntegerArray(10000, 0, 10000);
        SortGeneratorHelper.testSort("quickSort",new QuickSort(),arr,10000);
        SortGeneratorHelper.printArray(arr);
        System.out.println();
        System.out.println(SortGeneratorHelper.isSorted(arr,10000));
    }
}
