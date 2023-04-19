package A_One.T13;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和。
 */
public class C18 {

    public static int jobOne(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr, 0, sum/2);
    }

    public static int process(int[] arr, int index, int rest){
        if (index == arr.length){
            return 0;
        }
        int p1 = process(arr, index+1, rest);
        int p2 = 0;
        if (arr[index]<=rest){
            p2 = process(arr, index+1, rest-arr[index])+arr[index];
        }
        return Math.max(p1, p2);
    }

    public static int jobTwo(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int rest = sum/2;
        int N = arr.length;
        int[][] dpMap = new int[N+1][rest+1];
        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= rest; j++) {
                int p1 = dpMap[i+1][j];
                int p2 = 0;
                if (arr[i]<=j){
                    p2 = dpMap[i+1][j-arr[i]]+arr[i];
                }
                dpMap[i][j] = Math.max(p1, p2);
            }
        }
        return dpMap[0][rest];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,2};
        System.out.println(jobOne(arr));
        System.out.println(jobTwo(arr));
    }

}
