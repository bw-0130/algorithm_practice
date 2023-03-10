package F13_dynamicPlanning;

/**
 * 机器人走路：
 * 假设有排成一行的N个位置，记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上（M一定是1~N中的一个）
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置（P也是1~N中的一个）的方法有多少种；
 * 给定四个参数N、M、K、P，返回方法数。
 */
public class C01_RobotWalk {

    //暴力递归方法
    public static int jobOne(int N, int M, int K, int P) {
        return process(N, M, K, P);
    }

    /**
     * @param N 一共N个位置
     * @param M 机器人当前位置
     * @param K 剩余步数
     * @param P 最终主要到达位置
     * @return
     */
    public static int process(int N, int M, int K, int P) {
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        //当M在1位置时只能到2
        if (M == 1){
            return process(N, 2, K - 1, P);
        }
        //当M在N位置时只能到N-1
        if (M == N){
            return process(N, N - 1, K - 1, P);
        }
        //M在其他普遍位置
        return process(N, M - 1, K - 1, P) + process(N, M + 1, K - 1, P);
    }

    //动态规划方法
    public static int jobTwo(int N, int M, int K, int P){
        int[][] dpMap = new int[K+1][N+1];
        dpMap[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            dpMap[i][1] =  dpMap[i-1][2];
            dpMap[i][N] =  dpMap[i-1][N-1];
            for (int j = 2; j <= N - 1; j++) {
                dpMap[i][j] = dpMap[i-1][j-1] + dpMap[i-1][j+1];
            }
        }
        return dpMap[K][M];
    }

    public static void main(String[] args) {
        int N = 5;
        int M = 2;
        int K = 6;
        int P = 4;
        System.out.println(jobOne(N, M, K, P));
        System.out.println(jobTwo(N, M, K, P));
    }

}
