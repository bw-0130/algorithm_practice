package F25_ArrayThreeQuestion;

/**
 * 长度为N的正方形矩阵，绕圈打印*
 * 例：  * * * * *
 *               *
 *         * *   *
 *         *     *
 *         * * * *
 */
public class C08_PrintStar {

    public static void job(int N) {
        int leftUp = 0;
        int rightDown = N - 1;
        char[][] matrix = new char[N][N];
        //初始化矩阵
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = ' ';
            }
        }
        while (leftUp <= rightDown){
            set(matrix, leftUp, rightDown);
            leftUp += 2;
            rightDown -= 2;
        }
        //打印
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void set(char[][] matrix, int leftUp, int rightDown){
        for (int col = leftUp; col <= rightDown; col++) {//上面的*
            matrix[leftUp][col] = '*';
        }
        for (int row = leftUp + 1; row <= rightDown; row++) {//右面竖列的*
            matrix[row][rightDown] = '*';
        }
        for (int col = rightDown-1; col > leftUp; col--) {
            matrix[rightDown][col] = '*';
        }
        for (int row = rightDown-1; row > leftUp + 1; row--) {
            matrix[row][leftUp+1] = '*';
        }
    }

    public static void main(String[] args) {
        job(5);
    }

}
