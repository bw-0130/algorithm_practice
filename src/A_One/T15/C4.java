package A_One.T15;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * 返回全部由1组成的最大子矩形，内部有多少个1。
 */
public class C4 {

    public static int job(char[][] matrix) {
        int size = matrix[0].length;
        int[] arr = new int[size];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < size; j++) {
                arr[j] = matrix[i][j] == '1' ? arr[j] + 1 : 0;
            }
            res = Math.max(res, process(arr));
        }
        return res;
    }

    public static int process(int[] arr) {
        int size = arr.length;
        int res = Integer.MIN_VALUE;
        int[] stack = new int[size];
        int offSet = -1;
        for (int i = 0; i < size; i++) {
            while (offSet != -1 && arr[stack[offSet]] >= arr[i]) {
                int pop = stack[offSet--];
                int left = offSet == -1 ? -1 : stack[offSet];
                res = Math.max(res, (i - left - 1) * arr[pop]);
            }
            stack[++offSet] = i;
        }
        while (offSet != -1) {
            int pop = stack[offSet--];
            int left = offSet == -1 ? -1 : stack[offSet];
            res = Math.max(res, (size - left - 1) * arr[pop]);
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
         * 输出：6
         */
        char[][]  matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(job(matrix));
    }

}
