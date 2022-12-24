package F02_sort;

public class sort_util {

    public static void swap(int[] data, int a, int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static int[] createArray(int len, int maxValue){
        int[] res = new int[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (1+ Math.random()*maxValue);
        }
        return res;
    }

    public static void printArray(int[] data){
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]+" ");
        }
        System.out.println();
    }

}
