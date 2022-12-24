package F19_bfprt;

import javax.swing.*;
import java.util.Arrays;

/**
 * 给定一个无序的数组arr中，给定一个正数k，返回top k个最大的数
 * 不同时间复杂度的三个方法
 * <p>
 * 1、O(N*logN) 直接排序然后获取K个最大的数
 * 2、O(N+K*logN)从底向上建堆
 * 3、O(N+K*logK)
 */
public class C02_MaxTopK {

    //O(N*logN)
    public static int[] jobOne(int[] arr, int k) {
        if (arr == null && arr.length == 0) {
            return new int[0];
        }
        k = Math.min(k, arr.length);
        Arrays.sort(arr);
        int[] res = new int[k];
        for (int i = 0, j = arr.length - 1; i < k; i++, j--) {
            res[i] = arr[j];
        }
        return res;
    }

    //O(N+K*logN)
    public static int[] jobTwo(int[] arr, int k) {
        if (arr == null && arr.length == 0) {
            return new int[0];
        }
        k = Math.min(k, arr.length);
        int n = arr.length;
        //从底向上建立大根堆
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        int heapSize = n;
        swap(arr, 0, --heapSize);
        int count = 1;
        while (heapSize > 0 && count < k) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
            count++;
        }
        int[] res = new int[k];
        for (int i = 0, j = arr.length - 1; i < k; i++, j--) {
            res[i] = arr[j];
        }
        return res;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int last = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            last = arr[last] > arr[index] ? last : index;
            if (last == index) {
                break;
            }
            swap(arr, last, index);
            index = last;
            left = index * 2 + 1;
        }
    }

    //O(N+K*logK)
    public static int[] jobThree(int[] arr, int k) {
        if (arr == null && arr.length == 0) {
            return new int[0];
        }
        k = Math.min(k, arr.length);
        int n = arr.length;
        int mid = midKth(arr, n - k-1);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i]>mid){
                res[index++] = arr[i];
            }
        }
       /* for (;index<k;index++){
            res[index] = mid;
        }*/
        Arrays.sort(res);
        for (int l = 0,r = k-1;l<r;l++,r--){
            swap(res,l,r);
        }
        return res;
    }

    public static int midKth(int[] arr, int index) {
        int l = 0;
        int r = arr.length - 1;
        int pivot = 0;
        while (l < r) {
            pivot = arr[l + (int) Math.random() * (r - l + 1)];
            int[] partation = partation(arr, l, r, pivot);
            if (index < partation[0]) {
                r = partation[0] - 1;
            } else if (index > partation[1]) {
                l = partation[1] + 1;
            } else {
                return arr[index];
            }
        }
        return arr[l];
    }

    public static int[] partation(int[] arr, int l, int r, int pivot) {
        int winl = l - 1;
        int winr = r + 1;
        int index = l;
        while (index < winr) {
            if (arr[index] < pivot) {
                swap(arr, index++, ++winl);
            } else if (arr[index] > pivot) {
                swap(arr, index, --winr);
            } else {
                index++;
            }
        }
        return new int[]{winl + 1, winr - 1};
    }

    public static void main(String[] args) {
        int[] arr = {10, 8, 7, 36, 54, 35, 12, 89};
        int[] jobOne = jobOne(arr, 3);
        for (int data : jobOne) {
            System.out.print(data + " ");
        }
        System.out.println();
        int[] jobTwo = jobTwo(arr, 3);
        for (int data : jobTwo) {
            System.out.print(data + " ");
        }
        System.out.println();
        int[] jobThree = jobThree(arr, 3);
        for (int data : jobThree) {
            System.out.print(data + " ");
        }
        System.out.println();
    }

}
