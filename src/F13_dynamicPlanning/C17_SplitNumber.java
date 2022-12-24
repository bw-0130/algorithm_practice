package F13_dynamicPlanning;

/**
 * 给定一个正数1，裂开方法有一种{1}，给定一个正数2，裂开方法有两种{1、1}{2}，给定一个正数3，裂开方法有三种{1、1、1}{1、2}{3}
 * 给定一个正整数n，求裂开的方法数。
 */
public class C17_SplitNumber {

    //暴力递归方法
    public static int jobOne(int aim) {
        if (aim == 0) {
            return 0;
        }
        return process(1, aim);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int way = 0;
        for (int first = pre; first <= rest; first++) {
            way += process(first, rest - first);
        }
        return way;
    }

    //动态规划方法
    public static int jobTwo(int aim) {
        if (aim == 0) {
            return 0;
        }
        int[][] dpMap = new int[aim + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[i][0] = 1;
            dpMap[i][i] = 1;
        }
        for (int i = aim - 1; i >= 1; i--) {
            for (int j = i + 1; j <= aim; j++) {
                int way = 0;
                for (int first = i; first <= j; first++) {
                    way += dpMap[first][j - first];
                }
                dpMap[i][j] = way;
            }
        }
        return dpMap[1][aim];
    }

    //动态规划斜率优化
    public static int jobThree(int aim){
        if (aim == 0) {
            return 0;
        }
        int[][] dpMap = new int[aim + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[i][0] = 1;
            dpMap[i][i] = 1;
        }
        for (int i = aim - 1; i >= 1; i--) {
            for (int j = i + 1; j <= aim; j++) {
                dpMap[i][j] = dpMap[i+1][j] + dpMap[i][j-i];
            }
        }
        return dpMap[1][aim];
    }

    public static void main(String[] args) {
        int aim = 10;
        System.out.println(jobOne(aim));
        System.out.println(jobTwo(aim));
        System.out.println(jobThree(aim));
    }

}
