package com.cookies.demo.controller;

public class Test {

    public static void main(String[] args) {
        int[] a = {150, 100, 105, 85, 91, 82};

        int max_diff = a[1] - a[0];
        int min_num = a[0];
        for(int i=1; i < a.length; i++) {
            if(a[i] - min_num > max_diff) {
                max_diff = a[i] - min_num;
            }
            if(a[i] <  min_num) {
                min_num= a[i];
            }
        }
        System.out.println(max_diff);
    }

}
