package A_One;

/**
 * 选择排序
 */
public class CS2 {

    public static void sortJob(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            int minindex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[minindex]>data[j]){
                    minindex = j;
                }
            }
            sort_util.swap(data,minindex, i);
        }
    }

    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }

}
