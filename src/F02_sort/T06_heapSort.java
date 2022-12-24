package F02_sort;

/**
 * 堆排序
 */
public class T06_heapSort {

    public static void heapInsert(int[] data, int index) {
        while (data[index] > data[(index - 1) / 2]) {
            sort_util.swap(data, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] data, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int last = left + 1 < heapSize && data[left + 1] > data[left] ? left + 1 : left;
            last = data[index] > data[last] ? index : last;
            if (last == index) {
                break;
            }
            sort_util.swap(data, index, last);
            index = last;
            left = index * 2 + 1;
        }
    }

    public static void sort(int[] data){
        if (data == null || data.length < 2){
            return;
        }
        for (int i = 0; i < data.length; i++) {
            heapInsert(data,i);
        }
        int heapSize = data.length;
        while (heapSize>0){
            sort_util.swap(data, 0, heapSize-1);
            heapify(data,0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int[] array = sort_util.createArray(50, 100);
        sort_util.printArray(array);
        sort(array);
        sort_util.printArray(array);
    }

}
