package A_One.T13;

/**
 * 给定三个参数，N、M、K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * 注意：血量小于0的情况也要算
 */
public class C15 {

    public static double jobOne(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);//总次数
        return process(N, K, M) / all;
    }

    public static double process(int hp, int num, int M) {
        if (num == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return Math.pow(M + 1, num);
        }
        int res = 0;
        for (int i = 0; i <= M; i++) {
            res += process(hp - i, num - 1, M);
        }
        return res;
    }

    public static double jobTwo(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);//总次数
        double[][] dpMap = new double[K + 1][N + 1];
        dpMap[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dpMap[i][0] = Math.pow(M + 1, i);
            for (int j = 1; j <= N; j++) {
                int res = 0;
                for (int num = 0; num <= M; num++) {
                    if (j - num >= 0) {
                        res += dpMap[i - 1][j - num];
                    } else {
                        res += Math.pow(M + 1, i-1);
                    }
                }
                dpMap[i][j] = res;
            }
        }
        return dpMap[K][N] / all;
    }

    //斜率优化
    public static double jobThree(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);//总次数
        double[][] dpMap = new double[K + 1][N + 1];
        dpMap[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dpMap[i][0] = Math.pow(M + 1, i);
            for (int j = 1; j <= N; j++) {
                dpMap[i][j] += dpMap[i - 1][j] + dpMap[i][j - 1];
                if (j - 1 - M >= 0) {
                    dpMap[i][j] -= dpMap[i-1][j - 1 - M];
                }else {
                    dpMap[i][j] -= Math.pow(M+1, i-1);
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
