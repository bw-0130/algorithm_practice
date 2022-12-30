package F25_ArrayThreeQuestion;

/**
 * 斜线打印矩阵
 * 例:
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 打印顺序：1 2 5 9 6 3 4 7 10 11 8 12
 * 思路：按斜线打印，通过布尔类型参数控制打印方向
 */
public class C07_ZigZagPrintMatrix {

    public static void job(int[][] matrix) {
        int upR = 0;
        int upC = 0;
        int downR = 0;
        int downC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        System.out.println("endR:"+endR+" endC:"+endC);
        boolean printDirection = false;//打印方向：true从上向下打印，false从下向上打印
        while (upR != endR + 1) {
            printEnge(matrix, upR, upC, downR, downC, printDirection);
            //注意顺序
            upR = upC == endC ? upR + 1 : upR;
            upC = upC == endC ? upC : upC + 1;
            downC = downR == endR ? downC + 1 : downC;
            downR = downR == endR ? downR : downR + 1;
            printDirection = !printDirection;
        }
    }

    public static void printEnge(int[][] matrix, int upR, int upC, int downR, int downC, boolean printDirection) {
        System.out.print(" upR:"+upR+" upC:"+upC+" downR:"+downR+" downC:"+downC+" ~~ ");
        if (printDirection) {
            while (upR != downR + 1) {
                System.out.print(matrix[upR++][upC--] + " ");
            }
        } else {
            while (downR != upR-1){
                System.out.print(matrix[downR--][downC++]+" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        job(matrix);
    }

}
