package F15_monotonousStack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * <p>
 * 返回全部由1组成的子矩形数量。
 */
public class C05_CountSubmatricesWithAllOnes {

    public static int job(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int[] arr = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                arr[j] = matrix[i][j] == 0 ? 0 : arr[j] + 1;
            }
            res += process(arr);
        }
        return res;
    }

    public static int process(int[] arr) {

        int size = arr.length;
        int res = 0;
        int[] stack = new int[size];
        int sts = -1;
        for (int i = 0; i < size; i++) {
            while (sts != -1 && arr[stack[sts]] >= arr[i]) {
                int popIndex = stack[sts--];
                //等于情况最后算
                if (arr[popIndex] > arr[i]) {
                    int left = sts == -1 ? -1 : stack[sts];
                    int right = i;
                    int curNum = arr[popIndex];
                    int maxNum = Math.max(arr[right],left==-1?0:arr[left]);
                    int n = curNum-maxNum;
                    res += n*getNum(right-left-1);
                }
            }
            stack[++sts] = i;
        }
        while (sts!=-1){
            int popIndex = stack[sts--];
            int left = sts == -1 ? -1 : stack[sts];
            int right = size;
            int curNum = arr[popIndex];
            int maxNum = left==-1?0:arr[left];
            int n = curNum-maxNum;
            res += n*getNum(right-left-1);
        }
        return res;
    }

    public static int getNum(int n) {
        return (n * (n + 1)) >> 1;
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
