package com.xfj.sort.interfac;

/**
 * Created by asus on 2017/4/15.
 */
public interface Sort {
    public <T extends Comparable> void  sort(T[] arr,int n);
}
