package F02_sort;

/**
 * 快速排序
 */
public class T04_quickSort {

    public static void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        process(data, 0, data.length - 1);
    }

    public static void process(int[] data, int l, int r) {
        if (l > r) {
            return;
        }
        int[] partition = partition(data, l, r);
        process(data, l, partition[0] - 1);
        process(data, partition[1] + 1, r);
    }

    public static int[] partition(int[] data, int l, int r) {
        if (l == r) {
            return new int[]{l, r};
        }
        if (l > r) {
            return new int[]{-1, -1};
        }
        int winl = l-1;
        int winr = r;
        int index = l;
        while (index<winr){
            if (data[index]<data[r]){
                sort_util.swap(data, ++winl, index++);
            }else if (data[index]>data[r]){
                sort_util.swap(data, --winr, index);
            }else {
                index++;
            }
        }
        sort_util.swap(data,winr, r);
        return new int[]{winl+1, winr};
    }

    public static void main(String[] args) {
        int[] array = sort_util.createArray(10, 100);
        sort_util.printArray(array);
        sort(array);
        sort_util.printArray(array);
    }
}
