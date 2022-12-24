package F02_sort;

/**
 * 桶排序-计数排序
 */
public class T07_countSort {

    public static void sort(int[] data){
        if (data == null || data.length < 2){
            return;
        }
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            maxNum = Math.max(maxNum, data[i]);
        }
        int[] count = new int[maxNum+1];
        for (int i = 0; i < data.length; i++) {
            count[data[i]]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-->0){
                data[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = sort_util.createArray(50, 100);
        sort_util.printArray(array);
        sort(array);
        sort_util.printArray(array);
    }

}
