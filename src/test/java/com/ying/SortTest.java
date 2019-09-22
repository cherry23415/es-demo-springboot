package com.ying;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {
        int a[] = {65, 32, 8, 44, 78, 29, 33, 89};
//        insertSort(a);
//        bubbleSort(a);
//        quickSort(a, 0, a.length - 1);
//        selectSort(a);
        System.out.println(Arrays.toString(a));
        int[] newArr = mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(newArr));
        int m = 2;
        int n = 2 * ++m;
        System.out.println(m + " " + n);
    }

    //归并排序
    static int[] mergeSort(int[] a, int low, int high) {
        if (low == high)
            return new int[]{a[low]};

        int mid = (low + high) / 2;
        int[] leftA = mergeSort(a, low, mid);//左边归并排序
        int[] rightA = mergeSort(a, mid + 1, high);//右边归并排序
        int[] newArr = new int[leftA.length + rightA.length];//合并两个子序列
        int n = 0, i = 0, j = 0;
        while (i < leftA.length && j < rightA.length) {
            if (leftA[i] < rightA[j]) {
                newArr[n++] = leftA[i++];
            } else {
                newArr[n++] = rightA[j++];
            }
        }
        while (i < leftA.length) {
            newArr[n++] = leftA[i++];
        }
        while (j < rightA.length) {
            newArr[n++] = rightA[j++];
        }
        return newArr;
    }

    //插入排序
    static void insertSort(int[] a) {
        int j;
        int tmp;
        for (int i = 1; i < a.length; i++) {
            tmp = a[i];
            for (j = i; j > 0 && tmp < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    //冒泡排序
    static void bubbleSort(int[] a) {
        int tmp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1 && a[j] > a[j + 1]; j++) {
                tmp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = tmp;
            }
        }
    }

    //快速排序
    static void quickSort(int[] a, int left, int right) {
        if (left >= right) return;
        int i = left;
        int j = right;
        int base = a[left];
        while (i < j) {
            while (a[j] >= base && i < j) {
                j--;
            }
            a[i] = a[j];
            while (a[i] <= base && i < j) {
                i++;
            }
            a[j] = a[i];
        }

        a[i] = base;
        quickSort(a, left, i);
        quickSort(a, i + 1, right);
    }

    //选择排序
    static void selectSort(int[] a) {
        int tmpIn;
        int tmp;
        for (int i = 0; i < a.length; i++) {
            tmpIn = i;
            tmp = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if (a[tmpIn] > a[j])
                    tmpIn = j;
            }
            if (tmpIn != i) {
                a[i] = a[tmpIn];
                a[tmpIn] = tmp;
            }
        }
    }
}
