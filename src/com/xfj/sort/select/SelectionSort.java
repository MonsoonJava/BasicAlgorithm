package com.xfj.sort.select;

import com.xfj.sort.interfac.Sort;
import com.xfj.sort.pojo.Student;
import com.xfj.sort.util.SortGeneratorHelper;
import com.xfj.sort.util.SwapUtil;

/**
 * Created by asus on 2017/4/14.
 */
public class SelectionSort implements Sort{

    public <T extends Comparable> void  sort(T[] arr,int n){
        for (int i = 0;i < n - 1  ;i++){
            int minIndex = i;
            //选这个此次循环中最小的数值所在的索引位置，然后调换
            for (int j = i +1 ;j<n;j++){
                //if(arr[i] > arr[j]) minIndex = j;
                if(arr[minIndex].compareTo(arr[j]) > 0){
                    minIndex = j;
                }
            }
            if(i != minIndex){
               SwapUtil.swap(arr,i,minIndex);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{10,9,8,3,7,6,1,4,3,2,1};
        System.out.println( arr[1] instanceof Comparable);
        Sort sort = new SelectionSort();
        sort.sort(arr,arr.length);
        for(int i : arr){
            System.out.print(i + " ");
        }
        Student[] std = new Student[]{
                new Student("xfj",15),
                new Student("sc",14),
                new Student("xws",13),
                new Student("xass",12),
        };
        sort.sort(std,std.length);
        for(Student s : std){
            System.out.println(s + " ");
        }

        Integer[] testArr = SortGeneratorHelper.generatorIntegerArray(10000,0,10000);
/*
        SortGeneratorHelper.printArray(testArr);
        System.out.println(" ");
        sort.sort(testArr,testArr.length);
        SortGeneratorHelper.printArray(testArr);
*/

        SortGeneratorHelper.testSort("selectsort",sort,testArr,10000);
        SortGeneratorHelper.printArray(testArr);
    }

}
