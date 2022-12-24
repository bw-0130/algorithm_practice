package F13_dynamicPlanning;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1,返回1
 * n=2或3, 2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class C20_NQueens {

    public static int jobOne(int n){
        if (n<1){
            return 0;
        }
        int[] record = new int[n];
        return process(record, 0, n);
    }

    public static int process(int[] record, int index, int n){
        if (index == n){
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (check(record,index,j)){
                record[index] = j;
                res += process(record, index+1, n);
            }
        }
        return res;
    }
    //之前行皇后位置（x，y），当前皇后位置（甲，乙），判断共斜线：甲-x == y-乙
    public static boolean check(int[] record, int index, int j){
        for (int k = 0; k < index; k++) {
            if (j==record[k]||Math.abs(index-k)==Math.abs(record[k]-j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(jobOne(n));
    }

}
