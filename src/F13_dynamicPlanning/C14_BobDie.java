package F13_dynamicPlanning;

/**
 * 给定五个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在（row，col）位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回K步之后，Bob还在N*M的区域的概率。
 * 思路：总生存点数除4^k
 */
public class C14_BobDie {

    //暴力递归方法
    public static double jobOne(int N, int M, int row, int col, int k) {
        if (row < 0 || row > N || col < 0 || col > M || k < 0) {
            return 0;
        }
        return process(N, M, row, col, k) / Math.pow(4,k);
    }

    public static int process(int N, int M, int row, int col, int rest) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int way = 0;
        way += process(N, M, row + 1, col, rest - 1);
        way += process(N, M, row - 1, col, rest - 1);
        way += process(N, M, row, col - 1, rest - 1);
        way += process(N, M, row, col + 1, rest - 1);
        return way;
    }

    //动态规划方法
    public static double jobTwo(int N, int M, int row, int col, int k){
        if (row < 0 || row > N || col < 0 || col > M || k < 0) {
            return 0;
        }
        int[][][] dpMap = new int[M][N][k+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dpMap[i][j][0] = 1;
            }
        }
        for (int z = 1; z <= k ; z++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    int way = getData(dpMap, N, M, x-1, y, z-1);
                    way += getData(dpMap, N, M, x+1, y, z-1);
                    way += getData(dpMap, N, M, x, y-1, z-1);
                    way += getData(dpMap, N, M, x, y+1, z-1);
                    dpMap[x][y][z] = way;
                }
            }
        }
        return dpMap[row][col][k]/Math.pow(4,k);
    }

    public static int getData(int[][][] dpMap, int N, int M, int row, int col, int k){
        if (row < 0 || row == N || col < 0 || col == M) {
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
