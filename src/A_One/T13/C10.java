package A_One.T13;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加起来就是距离累加和
 * 返回最小距离的累加和
 */
public class C10 {

    public static int jobOne(int[][] matrix) {
        int i = matrix.length;
        int j = matrix[0].length;
        return process(matrix, i - 1, j - 1);
    }

    public static int process(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return matrix[i][j];
        }
        int p1 = process(matrix, i - 1, j);
        int p2 = process(matrix, i, j - 1);
        return Math.min(p1, p2) + matrix[i][j];
    }

    public static int jobTwo(int[][] matrix){
        int i = matrix.length;
        int j = matrix[0].length;
        int[][] dpMap = new int[i][j];
        dpMap[0][0] = matrix[0][0];
        for (int row = 1; row < i; row++) {
            dpMap[row][0] = matrix[row][0] + dpMap[row-1][0];
        }
        for (int col = 1; col < j; col++) {
            dpMap[0][col] = matrix[0][col] + dpMap[0][col-1];
        }
        for (int k = 1; k < i; k++) {
            for (int l = 1; l < j; l++) {
                int p1 = dpMap[k-1][l];
                int p2 = dpMap[k][l-1];
                dpMap[k][l] = Math.min(p1, p2) + matrix[k][l];
            }
        }
        return dpMap[i-1][j-1];
    }

    //空间压缩动态规划
    public static int jobThree(int[][] matrix){
        int i = matrix.length;
        int j = matrix[0].length;
        int[] dpMap = new int[j];
        dpMap[0] = matrix[0][0];
        for (int col = 1; col < j; col++) {
            dpMap[col] = matrix[0][col]+dpMap[col-1];
        }
        for (int row = 1; row < i; row++) {
            dpMap[0] = dpMap[0]+matrix[row][0];
            for (int col = 1; col < j; col++) {
                dpMap[col] = matrix[row][col]+Math.min(dpMap[col], dpMap[col-1]);
            }
        }
        return dpMap[j-1];
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
