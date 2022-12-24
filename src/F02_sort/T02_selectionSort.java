package F02_sort;

/**
 * 选择排序
 */
public class T02_selectionSort {

    public static void sort(int[] data){
        if (data == null || data.length < 2){
            return;
        }
        for (int i = 0; i < data.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < data.length; j++) {
                if (data[minIndex]>data[j]){
                    minIndex = j;
                }
            }
            sort_util.swap(data, minIndex, i);
        }
    }

    public static void main(String[] args) {
        int[] array = sort_util.createArray(10, 100);
        sort_util.printArray(array);
        sort(array);
        sort_util.printArray(array);
    }

}
