package com.xfj.sort.merge;

import com.xfj.sort.insert.InsertSort;
import com.xfj.sort.interfac.Sort;
import com.xfj.sort.select.SelectionSort;
import com.xfj.sort.util.SortGeneratorHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by asus on 2017/4/15.
 */
public class MergeSort<T> implements Sort{
    @Override
    public <T extends Comparable> void sort(T[] arr, int n) {
        __mergeSort(arr,0,n -1);
    }

    private <T extends Comparable> void __mergeSort(T[] arr,int l,int h){
        //递归退出条件
        //if(l >= h) return ;
        if( h - l <= 15) {
            //当要排序数组小到一定值后，插入排序的性能要好于递归
            InsertSort.sortPart(arr,l,h);
            return;
        }
        int mid = (l + h)/ 2;
        __mergeSort(arr,l,mid);
        __mergeSort(arr,mid + 1,h);
        if(arr[mid].compareTo(arr[mid+1]) > 0){
            __merge(arr,l,mid,h);
        }
    }

    private  <T extends Comparable> void __merge(T[] arr,int l,int mid,int h){
        T[] aux = (T[]) createArray(arr[0].getClass(),h - l + 1);
        for(int i = l ;i <= h;i++){
            aux[i - l] = arr[i];
        }
        int i ;
        int j ;
        int k = l;
        for(i=l,j= mid +1;k <= h;k++)
        {
            if(i > mid){
                arr[k] = aux[j - l];
                j++;
            }else if( j > h){
                arr[k] = aux[i - l];
                i++;
            }else if(aux[i - l].compareTo(aux[j - l ]) < 0){
                arr[k] = aux[i - l];
                i++;
            }else{
                arr[k] = aux[j -l ];
                j++;
            }
        }
    }

    private  <T extends Comparable> T[] createArray(Class<T> clazz,int initialCapacity){
        //创建泛型数组的方法。
        return (T[])Array.newInstance(clazz,initialCapacity);

    }

    public static void main(String[] args) {
        Integer[] arr = SortGeneratorHelper.generatorIntegerArray(10000, 0, 10000);
        SortGeneratorHelper.printArray(arr);
        Sort sort = new MergeSort<Integer>();
        System.out.println("");
        sort.sort(arr,10000);
        SortGeneratorHelper.printArray(arr);
        System.out.println();
        System.out.println(SortGeneratorHelper.isSorted(arr,10000));
       /* Sort iort = new InsertSort();
        iort.sort(arr,10000);*/
    }
}
