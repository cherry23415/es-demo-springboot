package com.ying;

public class SearchTest {
    public static void main(String[] args) {
        int[] a = {2, 8, 12, 34, 45, 46, 77, 87, 91, 99, 125};
        System.out.println(sequentialSearch(a, 77));
        System.out.println(binarySearch(a, 125));
    }

    //顺序查找
    static int sequentialSearch(int[] a, int key) {
        int t = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                t = i;
                break;
            }
        }
        return t;
    }

    //二分查找
    static int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int m;
        while (low <= high) {
            m = (low + high) / 2;
            if (key > a[m]) {
                low = m + 1;
            } else if (key < a[m]) {
                high = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
