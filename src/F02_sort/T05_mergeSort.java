package F02_sort;

/**
 * 归并排序
 */
public class T05_mergeSort {

    public static void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        process(data, 0, data.length - 1);
    }

    public static void process(int[] data, int l, int r) {
        if (l == r){
            return;
        }
        int midIndex = l + (r-1)>>1;
        System.out.println(l+" "+r+" "+ midIndex);
        process(data, l, midIndex);
        process(data, midIndex+1, r);
        merge(data, l, midIndex, r);
    }

    public static void merge(int[] data, int l, int midIndex, int r){
        int[] temp = new int[r-l+1];
        int winl = l;
        int winr = midIndex+1;
        int index = 0;
        while (winl<= midIndex && winr <= r){
            temp[index ++] = data[winl]<=data[winr]?data[winl++]:data[winr++];
        }
        while (winl<= midIndex){
            temp[index ++] = data[winl++];
        }
        while (winr <= r){
            temp[index ++] = data[winr++];
        }
        for (int i = 0; i < temp.length; i++) {
            data[l+i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] array = sort_util.createArray(10, 100);
        sort_util.printArray(array);
        sort(array);
        sort_util.printArray(array);
    }

}
