package A_One.T02;

import A_One.sort_util;

/**
 * 归并排序
 */
public class CS5 {

    public static void sortJob(int[] data){
        if (data==null || data.length<2){
            return;
        }
        process(data, 0, data.length-1);
    }

    public static void process(int[] data, int l, int r){
        if (l == r){
            return;
        }
        int mid = l + (r-1)>>1;
        process(data, l, mid);
        process(data, mid+1, r);
        merge(data, l, mid, r);
    }

    public static void merge(int[] data, int l, int mid, int r){
        int[] temp = new int[r-l+1];
        int winl = l;
        int winr = mid+1;
        int index = 0;
        while (winl<=mid && winr <= r){
            temp[index++] = data[winl]<=data[winr]?data[winl++]:data[winr++];
        }
        while (winl<=mid){
            temp[index++] = data[winl++];
        }
        while (winr <= r){
            temp[index++] = data[winr++];
        }
        for (int i = 0; i < temp.length; i++) {
            data[l+i] = temp[i];
        }
    }
    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }
}
