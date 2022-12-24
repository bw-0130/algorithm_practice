package F13_dynamicPlanning;

/**
 * 给定三个参数，N、M、K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * 注意：血量小于0的情况也要算
 */
public class C15_KillMonster {
    //暴力递归方法
    public static double jobOne(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        double process = (double) process(N, K, M);
        return process / all;
    }

    public static long process(int hp, int num, int M) {
        if (num == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(M + 1, num);
        }
        long way = 0;
        for (int i = 0; i <= M; i++) {
            way += process(hp - i, num - 1, M);
        }
        return way;
    }

    //动态规划方法
    public static double jobTwo(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long[][] dpMap = new long[K + 1][N + 1];
        dpMap[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dpMap[i][0] = (long) Math.pow(M + 1, i);
            for (int j = 1; j <= N; j++) {
                long way = 0;
                for (int m = 0; m <= M; m++) {
                    if (j - m >= 0) {
                        way += dpMap[i - 1][j - m];
                    } else {
                        way += (long) Math.pow(M + 1, i - 1);
                    }
                }
                dpMap[i][j] = way;
            }
        }
        return dpMap[K][N] / all;
    }

    //动态规划方法斜率优化
    public static double jobThree(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long[][] dpMap = new long[K + 1][N + 1];
        dpMap[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dpMap[i][0] = (long) Math.pow(M + 1, i);
            for (int j = 1; j <= N; j++) {
                dpMap[i][j] = dpMap[i][j - 1] + dpMap[i - 1][j];
                if (j - 1 - M >= 0) {
                    dpMap[i][j] -= dpMap[i - 1][j - 1 - M];
                } else {
                    dpMap[i][j] -= Math.pow(M + 1, i - 1);
                }
            }
        }
        return dpMap[K][N] / all;
    }

    public static void main(String[] args) {
        int N = 10;
        int M = 9;
        int K = 6;
        System.out.println(jobOne(N, M, K));
        System.out.println(jobTwo(N, M, K));
        System.out.println(jobThree(N, M, K));
    }

}
