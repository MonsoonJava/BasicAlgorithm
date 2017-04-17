package com.xfj.sort.insert;

import com.xfj.sort.interfac.Sort;
import com.xfj.sort.select.SelectionSort;
import com.xfj.sort.util.SortGeneratorHelper;
import com.xfj.sort.util.SwapUtil;

import java.util.Arrays;

/**
 * Created by asus on 2017/4/15.
 */
public class InsertSort implements Sort{
    @Override
    public <T extends Comparable> void sort(T[] arr, int n) {
        if(n < 1) return;
/*
        for(int i = 1;i < n;i++){
            for(int j = i ;j >0;j-- ){
                if(arr[j].compareTo(arr[j - 1 ]) < 0){
                    SwapUtil.swap(arr,j,j - 1);
                }else
                    break;
            }
        }
*/
        for(int i = 1;i< n;i++){
            T e = arr[i];
            int j;
            for(j= i;j > 0 && arr[j-1].compareTo(e) > 0;j--){
                arr[j] = arr[j-1];
            }
            arr[j] = e;
        }
    }


    public  static <T extends Comparable> void sortPart(T[] t,int low ,int high){
        if(t == null || t.length == 0) return;
        for(int i = low; i <= high ; i++){
            T e = t[i];
            int j;
            for (j = i; j > low && t[j-1].compareTo(e) >0 ;j--){
                t[j] = t[j-1];
            }
            t[j] = e;
        }
    }



    public static void main(String[] args) {
        Integer[] arrtest = SortGeneratorHelper.generatorIntegerArray(10000, 0, 10000);
        Sort sort = new InsertSort();
        sort.sort(arrtest,1000);
        SortGeneratorHelper.printArray(arrtest);
        System.out.println("");
        System.out.println(SortGeneratorHelper.isSorted(arrtest,1000));

        Integer[] clone = Arrays.copyOf(arrtest, 10000);
        SortGeneratorHelper.testSort("selectSort",new SelectionSort(),clone,10000);
        SortGeneratorHelper.printArray(clone);
        System.out.println("");;
        SortGeneratorHelper.testSort("insertSort",new InsertSort(),arrtest,10000);
        SortGeneratorHelper.printArray(arrtest);
    }


}
