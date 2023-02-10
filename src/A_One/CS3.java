package A_One;

/**
 * 插入排序
 */
public class CS3 {
    public static void sortJob(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        for (int i = 1; i < data.length; i++) {
            for (int j = i-1; j>=0 && data[j]>data[j+1];j--){
                sort_util.swap(data, j, j+1);
            }
        }
    }
    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }
}
