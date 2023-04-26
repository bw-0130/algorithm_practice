package A_One.T15;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * 返回全部由1组成的子矩形数量。
 */
public class C5 {

    public static int getNum(int n) {
        return (n * (n + 1)) >> 1;
    }

    public static int job(int[][] matrix) {
        int size = matrix[0].length;
        int res = 0;
        int[] arr = new int[size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < size; j++) {
                arr[j] = matrix[i][j] == 1 ? arr[j] + 1 : 0;
            }
            res += process(arr);
        }
        return res;
    }

    public static int process(int[] arr) {
        int size = arr.length;
        int res = 0;
        int[] stack = new int[size];
        int offSet = -1;
        for (int i = 0; i < size; i++) {
            while (offSet != -1 && arr[stack[offSet]] >= arr[i]) {
                int pop = stack[offSet--];
                int left = offSet == -1 ? -1 : stack[offSet];
                int minhight = Math.max(left == -1 ? 0 : arr[left], arr[i]);
                res += (arr[pop] - minhight) * getNum(i - left - 1);
            }
            stack[++offSet] = i;
        }
        while (offSet!=-1){
            int pop = stack[offSet--];
            int left = offSet == -1 ? -1 : stack[offSet];
            int minhight = left == -1 ? 0 : arr[left];
            res += (arr[pop] - minhight) * getNum(size - left - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入：mat = [[1,0,1],[1,1,0],[1,1,0]]
         * 输出：13
         */
        int[][] matrix = {{1,0,1},{1,1,0},{1,1,0}};
        System.out.println(job(matrix));
    }
}
