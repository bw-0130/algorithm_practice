package A_One.T02;

import A_One.sort_util;

/**
 * 堆排序
 */
public class CS6 {

    public static void heapInsert(int[] data, int index) {
        while (data[index] > data[(index - 1) / 2]) {
            sort_util.swap(data, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] data, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int temp = left + 1 < heapSize && data[left + 1] > data[left] ? left + 1 : left;
            temp = data[index]>data[temp]?index:temp;
            if (temp == index){
                break;
            }
            sort_util.swap(data, temp, index);
            index = temp;
            left = index*2+1;
        }
    }

    public static void sortJob(int[] data){
        if (data == null || data.length < 2) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            heapInsert(data, i);
        }
        int heapSize = data.length;
        while (heapSize>0){
            sort_util.swap(data,0,heapSize-1);
            heapify(data, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }

}
