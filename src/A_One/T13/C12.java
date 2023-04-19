package A_One.T13;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数。
 * 例如：1+1+1+1、1+1+2、2+2 一共就3种方法，所以返回3
 */
public class C12 {

    public static int jobOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        int N = arr.length;
        if (N == index) {
            return rest == 0 ? 1 : 0;
        }
        int way = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            way += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return way;
    }

    public static int jobTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        int N = arr.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int way = 0;
                for (int zhang = 0; zhang * arr[i] <= j; zhang++) {
                    way += dpMap[i + 1][j - (zhang * arr[i])];
                }
                dpMap[i][j] = way;
            }
        }
        return dpMap[0][aim];
    }

    //斜率优化
    public static int jobThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        int N = arr.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dpMap[i][j] = dpMap[i+1][j];
                if (j-arr[i]>=0){
                    dpMap[i][j] += dpMap[i][j-arr[i]];
                }
            }
        }
        return dpMap[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2};
        int aim = 4;
        System.out.println(jobOne(arr, aim));
        System.out.println(jobTwo(arr, aim));
        System.out.println(jobThree(arr, aim));
    }

}
