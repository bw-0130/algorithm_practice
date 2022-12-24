package F22_indexTree_AC;

/**
 * 二维indextree
 */
public class C02_indexTree {

    public static class indexTree{
        private int[][] tree;
        private int[][] num;
        private int N;
        private int M;

        public indexTree(int[][] data) {
            if (data.length == 0 || data[0].length == 0){
                return;
            }
            N = data.length;
            M = data[0].length;
            num = new int[N][M];
            tree = new int[N+1][M+1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    update(i, j, data[i][j]);
                }
            }
        }

        public void update(int i, int j, int val){
            if (i == 0 || j == 0){
                return;
            }
            int add = val - num[i][j];
            num[i][j] = val;
            for (int k = i+1; k <= N; k += k & (-k) ) {
                for (int l = j+1; l <= M; l += l & (-l)) {
                    tree[k][l] += add;
                }
            }
        }

        public int sum(int i, int j){
            if (M == 0 || N == 0){
                return 0;
            }
            int res = 0;
            for (int k = i+1; k > 0; k -= k & (-k)) {
                for (int l = j+1; l > 0; l -= l & (-l)) {
                    res += tree[k][l];
                }
            }
            return res;
        }

        public int getResult(int row1, int col1, int row2, int col2){
            if (M == 0 || N == 0){
                return 0;
            }
            return sum(row2, col2) - sum(row2, col1-1) - sum(row1-1, col2) + sum(row1-1, col1-1);
        }
    }

}
