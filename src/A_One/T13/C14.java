package A_One.T13;

/**
 * 给定五个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在（row，col）位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回K步之后，Bob还在N*M的区域的概率。
 * 思路：总生存点数除4^k
 */
public class C14 {

    public static double jobOne(int N, int M, int row, int col, int k) {
        if (row < 0 || row > N | col < 0 || col > M || k < 0) {
            return 0;
        }
        return process(N, M, row, col, k) / Math.pow(4, k);
    }

    public static int process(int N, int M, int row, int col, int rest) {
        if (row < 0 || row == N | col < 0 || col == M) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int way = 0;
        way += process(N, M, row - 1, col, rest - 1);
        way += process(N, M, row, col - 1, rest - 1);
        way += process(N, M, row + 1, col, rest - 1);
        way += process(N, M, row, col + 1, rest - 1);
        return way;
    }

    public static double jobTwo(int N, int M, int row, int col, int k) {
        if (row < 0 || row > N | col < 0 || col > M || k < 0) {
            return 0;
        }
        int[][][] dpMap = new int[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dpMap[i][j][0] = 1;
            }
        }
        for (int z = 1; z <= k; z++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int way = 0;
                    way += getData(N, M, i - 1, j, z - 1, dpMap);
                    way += getData(N, M, i, j - 1, z - 1, dpMap);
                    way += getData(N, M, i + 1, j, z - 1, dpMap);
                    way += getData(N, M, i, j + 1, z - 1, dpMap);
                    dpMap[i][j][z] = way;
                }
            }
        }
        return dpMap[row][col][k] / Math.pow(4, k);
    }

    public static int getData(int N, int M, int row, int col, int k, int[][][] dpMap) {
        if (row < 0 || row == N | col < 0 || col == M) {
            return 0;
        }
        return dpMap[row][col][k];
    }

    public static void main(String[] args) {
        int N = 50;
        int M = 50;
        int k = 10;
        int row = 6;
        int col = 6;
        System.out.println(jobOne(N,M,row,col,k));
        System.out.println(jobTwo(N,M,row,col,k));
    }

}
