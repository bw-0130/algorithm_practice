package A_One.T02;

import A_One.sort_util;

/**
 * 冒泡排序
 */
public class CS1 {

    public static void sortJob(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j]>data[j+1]){
                    sort_util.swap(data, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }

}
