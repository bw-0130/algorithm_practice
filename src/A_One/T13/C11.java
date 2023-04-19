package A_One.T13;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即使是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法。
 * 例如：arr = {1,1,1}， aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2，一共就三种方法，所以返回3.
 */
public class C11 {

    public static int jobOne(int[] arrs, int aim) {
        if (arrs == null || arrs.length == 0 || aim < 1) {
            return 0;
        }
        return process(arrs, 0, aim);
    }

    public static int process(int[] arrs, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (arrs.length == index) {
            return rest == 0 ? 1 : 0;
        }
        return process(arrs, index + 1, rest) + process(arrs, index + 1, rest - arrs[index]);
    }

    public static int jobTwo(int[] arrs, int aim) {
        if (arrs == null || arrs.length == 0 || aim < 1) {
            return 0;
        }
        int N = arrs.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int p1 = dpMap[i + 1][j];
                if (j - arrs[i] >= 0) {
                    p1 += dpMap[i + 1][j - arrs[i]];
                }
                dpMap[i][j] = p1;
            }
        }
        return dpMap[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1};
        int aim = 2;
        System.out.println(jobOne(arr, aim));
        System.out.println(jobTwo(arr, aim));
    }

}
