package com.xfj.sort.heap;

import com.xfj.sort.util.SwapUtil;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by asus on 2017/4/16.
 */
public class MaxHeap<T extends Comparable>{

    private T[] arr = null;

    private int count;

    private int capacity;

    public MaxHeap(int capacity,Class clazz){
        this.count = 0;
        this.capacity = capacity;
        this.arr = (T[]) Array.newInstance(clazz,capacity + 1);
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int size(){
        return count;
    }

    public void insert(T item){
        if(item != null && (count + 1<= capacity )){
            arr[count + 1] = item;
            count++;
            shiftUp(count);
        }
    }

    //将新加入的值一直进行上升，使数组保持完全二叉树
    private void shiftUp(int index){
        while ( index > 1 && arr[index/2].compareTo(arr[index]) < 0){
            //父节点如果小于子节点 ，则交换
            SwapUtil.swap(arr,index/2,index);
            index = index /2;
        }
    }

    public T extractMax(){
        assert (count > 0);
        T  item = arr[1];
        if(count / 2 >= 1 )
            SwapUtil.swap(arr,1,count);
            count--;
            shiftDown(1);
        return arr[1];
    }

    private void shiftDown(int index){
        //首先确保2*index有值，即该节点作为父节点必存在左子结点，可能存在右子节点
        while ((2 * index <= count)){
            int leftIndex = 2 * index;
            if(((leftIndex +1)<=count) && arr[leftIndex].compareTo(arr[leftIndex+1]) < 0)
                leftIndex = leftIndex + 1;
            if(arr[index].compareTo(arr[leftIndex]) > 0) break;
            SwapUtil.swap(arr,index,leftIndex);
            index = leftIndex;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        MaxHeap<Integer> heap = new MaxHeap<Integer>(100,Integer.class);
        for(int i = 0;i<100;i++){
            heap.insert(random.nextInt(100));
        }
        for(int i = 0;i<100;i++){
            System.out.print(heap.extractMax() + " ");
        }

    }



}
