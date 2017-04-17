package com.xfj.sort.pojo;

/**
 * Created by asus on 2017/4/14.
 */
public class Student implements Comparable<Student>{

    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        if(  o instanceof Student){
            if(this.age > o.age){
                return 1;
            }else if(this.age < o.age){
                return  -1;
            }else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
