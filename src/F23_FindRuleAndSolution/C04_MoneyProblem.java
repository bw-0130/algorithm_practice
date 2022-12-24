package F23_FindRuleAndSolution;

/**
 * int[] d, d[i]:i号怪兽的能力
 * int[] p, p[i]:i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前能力，小于i好怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力值直接累加到你的能力上；
 * 如果你当前能力，大于等于i号怪兽的能力，你可以选择通过，你的能力并不会下降，
 * 你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力值直接累加到你的能力上。返回通过所有怪兽，需要花的最小钱数？
 */
public class C04_MoneyProblem {

    //要通过0-index号怪兽必须要花money元钱，返回所需要的最大能力
    // 没有方案返回-1。适用money不大但是能力值很大的情况
    public static long processOne(int[] d, int[] p, int index, int money) {
        if (index == -1) {
            return money == 0 ? 0 : -1;
        }
        //不贿赂
        long p1 = -1;
        long temp1 = processOne(d, p, index - 1, money);
        if (temp1 != -1 && d[index] <= temp1) {//不贿赂index号怪兽有方案，返回的能力值够打败index号怪兽的能力
            p1 = temp1;
        }
        //贿赂
        long p2 = -1;
        long temp2 = processOne(d, p, index - 1, money - p[index]);
        if (temp2 != -1) {
            p2 = d[index] + temp2;
        }
        return Math.max(p1, p2);
    }

    public static long jobOne(int[] d, int[] p) {
        int N = d.length;
        int moneyAll = 0;
        for (int i = 0; i < p.length; i++) {
            moneyAll += p[i];
        }
        for (int i = 0; i < moneyAll; i++) {
            if (processOne(d, p, N - 1, i) != -1) {
                return i;
            }
        }
        return moneyAll;
    }

    //动态规划方法
    public static long dbOne(int[] d, int[] p) {
        int N = d.length;
        int moneyAll = 0;
        for (int i = 0; i < p.length; i++) {
            moneyAll += p[i];
        }
        long[][] dpMap = new long[N][moneyAll + 1];
        //初始化dp表
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= moneyAll; j++) {
                dpMap[i][j] = -1;
            }
        }
        //baseCase
        dpMap[0][p[0]] = d[0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= moneyAll; j++) {
                //不贿赂
                if (d[i] <= dpMap[i - 1][j]) {//不贿赂index号怪兽有方案，返回的能力值够打败index号怪兽的能力
                    dpMap[i][j] = dpMap[i - 1][j];
                }
                //贿赂
                long p2 = -1;
                if (j >= p[i] && dpMap[i - 1][j - p[i]] != -1) {//加入越界条件判断
                    dpMap[i][j] = Math.max(dpMap[i][j], d[i] + dpMap[i - 1][j - p[i]]);
                }
            }
        }
        for (int i = 0; i < moneyAll; i++) {
            if (dpMap[N - 1][i] != -1) {
                return i;
            }
        }
        return moneyAll;
    }

    //要通过index后续怪兽能力值为nengli，所需要最小钱数
    //适用能力值很小但是钱数范围很大的情况
    public static long processTwo(int[] d, int[] p, int index, int nengli) {
        if (index == d.length) {
            return 0;
        }
        if (nengli < d[index]) {
            //当前鞥能力小于index号怪兽所需能力
            return p[index] + processTwo(d, p, index + 1, nengli + d[index]);
        } else {
            return Math.min(p[index] + processTwo(d, p, index + 1, nengli + d[index]),
                    processTwo(d, p, index + 1, nengli));
        }
    }

    public static long jobTwo(int[] d, int[] p) {
        int N = d.length;
        return processTwo(d, p, 0, 0);
    }

    //动态规划方法
    public static long dbTwo(int[] d, int[] p) {
        int N = d.length;
        int nengliAll = 0;
        for (int i = 0; i < N; i++) {
            nengliAll += d[i];
        }
        long[][] dpMap = new long[N + 1][nengliAll + 1];
        //baseCase
        for (int i = 0; i < nengliAll; i++) {
            dpMap[N][i] = 0;
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= nengliAll; j++) {
                if (j + d[i] > nengliAll) {//越界条件判断
                    continue;
                }
                if (j < d[i]) {
                    //当前鞥能力小于index号怪兽所需能力
                    dpMap[i][j] = p[i] + dpMap[i + 1][j + d[i]];
                } else {
                    dpMap[i][j] = Math.min(p[i] + dpMap[i + 1][j + d[i]],
                            dpMap[i + 1][j]);
                }
            }
        }
        return dpMap[0][0];
    }

    public static int[][] createArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] res = new int[2][size];
        for (int i = 0; i < size; i++) {
            res[0][i] = (int) (Math.random() * value) + 1;
            res[1][i] = (int) (Math.random() * value) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            int[][] array = createArray(5, 10);
            int[] d = array[0];
            int[] p = array[1];
            if (jobOne(d, p) != jobTwo(d, p) || jobTwo(d, p) != dbOne(d, p) || jobTwo(d, p) != dbTwo(d, p)) {
                System.out.println("oops!");
            }
        }
        System.out.println("执行完毕！");
    }

}
