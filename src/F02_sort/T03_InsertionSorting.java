package F02_sort;

/**
 * 插入排序
 */
public class T03_InsertionSorting {

    public static void sort(int[] data){
        if (data == null || data.length < 2){
            return;
        }
        for (int i = 1; i < data.length; i++) {
            for (int j = i-1; j>=0 && data[j]>data[j+1] ; j--) {
                sort_util.swap(data, j, j+1);
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
