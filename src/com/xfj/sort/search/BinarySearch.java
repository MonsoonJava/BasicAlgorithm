package com.xfj.sort.search;

import com.xfj.sort.util.SortGeneratorHelper;

/**
 * Created by asus on 2017/4/17.
 */
public class BinarySearch {

    //在已经排序号的数组中的[lo,hi]中寻找是否存在target,有则返回其索引
    private static int search(int[] arr,int lo,int hi,int target){
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            else if(arr[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }


    private static int recursSearch(int[] arr,int lo,int hi,int target){
        //递归退出条件
        if(lo > hi) return -1;
        int mid = lo + ( hi - lo) / 2;
        if(arr[mid] == target) return mid;
        else if(arr[mid] > target) return recursSearch(arr,lo,mid - 1,target);
        else return recursSearch(arr,mid + 1,hi,target);
    }

    public static void main(String[] agrs){
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int index = BinarySearch.search(arr, 0, arr.length - 1, 15);
        System.out.println("with while  "+index);
        int index2 = BinarySearch.recursSearch(arr,0,arr.length -1 ,51);
        System.out.println("recurseSearch  " + index2);
    }

}
