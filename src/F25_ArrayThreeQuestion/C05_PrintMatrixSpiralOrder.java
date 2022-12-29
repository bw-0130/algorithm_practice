package F25_ArrayThreeQuestion;

/**
 * 矩阵按螺旋顺序打印
 * 思路：矩阵分全结构
 */
public class C05_PrintMatrixSpiralOrder {

    public static void jobOne(int[][] matrix) {
        int sr = 0;//左上行
        int sc = 0;//左上列
        int er = matrix.length - 1;//右下行
        int ec = matrix[0].length - 1;//右下列
        while (sr <= er && sc <= ec) {
            printEnge(matrix, sr++, sc++, er--, ec--);
        }
    }

    public static void printEnge(int[][] matrix, int sr, int sc, int er, int ec) {
        if (sr == er){
            for (int i = sc; i <= ec; i++) {
                System.out.print(matrix[sr][i] + "· ");
            }
        }else if (sc == ec){
            for (int i = sr; i <= er; i++) {
                System.out.print(matrix[i][sc] + "· ");
            }
        }else {
            int curr = sr;
            int curc = sc;
            while (curc != ec) {
                System.out.print(matrix[sr][curc] + "- ");
                curc++;
            }
            while (curr != er) {
                System.out.print(matrix[curr][ec] + "- ");
                curr++;
            }
            while (curc != sc) {
                System.out.print(matrix[er][curc] + "- ");
                curc--;
            }
            while (curr != sr){
                System.out.print(matrix[curr][sc]+ "- ");
                curr--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        jobOne(matrix);
    }

}
