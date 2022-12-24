package F15_monotonousStack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * 返回全部由1组成的最大子矩形，内部有多少个1。
 */
public class C04_MaximalRectangle {

    public static int job(char[][] matrix) {
        int[] arr = new int[matrix[0].length];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                arr[j] = matrix[i][j] == '1' ? arr[j] + 1 : 0;
            }
            res = Math.max(res, process(arr));
        }
        return res;
    }

    //自定数组替换stack
    public static int process(int[] arr) {
        int size = arr.length;
        int res = Integer.MIN_VALUE;
        int[] stack = new int[size];
        int sts = -1;
        for (int i = 0; i < size; i++) {
            while (sts != -1 && arr[stack[sts]] >= arr[i]) {
                int popInex = stack[sts--];
                int left = sts == -1?-1:stack[sts];
                res = Math.max(res, (i-left-1)*arr[popInex]);
            }
            stack[++sts] = i;
        }
        while (sts != -1){
            int popInex = stack[sts--];
            int left = sts == -1?-1:stack[sts];
            res = Math.max(res, (size-left-1)*arr[popInex]);
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
