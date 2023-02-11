package A_One;

/**
 * 桶排序（计数排序）
 * 11/02/2023 bianwei
 */
public class CS7 {

    public static void sortJob(int[] data){
        if (data==null || data.length<2){
            return;
        }
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            maxNum = Math.max(maxNum, data[i]);
        }
        int[] temp = new int[maxNum+1];
        for (int i = 0; i < data.length; i++) {
            temp[data[i]]++;
        }
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i]-->0){
                data[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = sort_util.createArray(10, 100);
        sortJob(data);
        sort_util.printArray(data);
    }

}