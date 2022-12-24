package F13_dynamicPlanning;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加起来就是距离累加和
 * 返回最小距离的累加和
 */
public class C10_MinPathSum {

    //暴力递归方法
    public static int jobOne(int[][] matrix) {
        int i = matrix.length;
        int j = matrix[0].length;
        return proccess(matrix, i - 1, j - 1);
    }

    public static int proccess(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return matrix[i][j];
        }
        int p1 = proccess(matrix, i - 1, j);
        int p2 = proccess(matrix, i, j - 1);
        return Math.min(p1, p2) + matrix[i][j];
    }

    //动态规划方法
    public static int jobTwo(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dpMap = new int[rows][cols];
        dpMap[0][0] = matrix[0][0];
        for (int row = 1; row < rows; row++) {
            dpMap[row][0] = dpMap[row-1][0] + matrix[row][0];
        }
        for (int col = 1; col < cols; col++) {
            dpMap[0][col] = dpMap[0][col-1] + matrix[0][col];
        }
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                int p1 = dpMap[row-1][col];
                int p2 = dpMap[row][col-1];
                dpMap[row][col] = Math.min(p1, p2) + matrix[row][col];
            }
        }
        return dpMap[rows-1][cols-1];
    }

    //动态规划空间压缩方法
    public static int jobThree(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dpMap = new int[cols];
        dpMap[0] = matrix[0][0];
        for (int col = 1; col < cols; col++) {
            dpMap[col] = dpMap[col-1] + matrix[0][col];
        }
        for (int i = 1; i < rows; i++) {
            dpMap[0] += matrix[i][0];
            for (int j = 1; j < cols; j++) {
                dpMap[j] = Math.min(dpMap[j-1],dpMap[j])+matrix[i][j];
            }
        }
        return dpMap[cols-1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        int index = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = index++;
            }
        }
        System.out.println(jobOne(matrix));
        System.out.println(jobTwo(matrix));
        System.out.println(jobThree(matrix));
    }

}
