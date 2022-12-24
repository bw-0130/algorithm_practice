package F02_sort;

/**
 * 冒泡排序
 */
public class T01_BubbleSort {

    public static void sort(int[] data){
        if (data == null || data.length < 2){
            return;
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j+1]){
                    sort_util.swap(data, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = sort_util.createArray(10, 100);
        sort_util.printArray(array);
        sort(array);
        sort_util.printArray(array);
    }

}
