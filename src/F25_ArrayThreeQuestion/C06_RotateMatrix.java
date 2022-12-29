package F25_ArrayThreeQuestion;

/**
 * 90°旋转矩阵
 */
public class C06_RotateMatrix {

    public static void job(int[][] matrix) {
        int sr = 0;
        int sc = 0;
        int er = matrix.length - 1;
        int ec = matrix[0].length - 1;
        while (sr < er) {
            rotateEnge(matrix, sr++, sc++, er--, ec--);
        }
    }

    public static void rotateEnge(int[][] matrix, int sr, int sc, int er, int ec) {
        int temp = 0;
        for (int i = 0; i < ec - sc; i++) {
            temp = matrix[sr][sc+i];
            matrix[sr][sc+i] = matrix[er-i][sc];//第二个点
            matrix[er-i][sc] = matrix[er][ec-i];//第三个点
            matrix[er][ec-i] = matrix[sr+i][ec];//第四个点
            matrix[sr+i][ec] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        job(matrix);//旋转
        System.out.println("=========");
        printMatrix(matrix);

    }

}
