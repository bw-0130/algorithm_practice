package A_One.T08;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class C2 {

    public static class UnionFind {
        public int[] parent;
        public int[] help;
        public int[] sizes;
        public int size;

        public UnionFind(int N) {
            parent = new int[N];
            help = new int[N];
            sizes = new int[N];
            size = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
        }

        public int findFather(int data) {
            int index = 0;
            while (parent[data] != data) {
                help[index++] = data;
                data = parent[data];
            }
            for (int i = index; i >= 0; i--) {
                parent[help[i]] = data;
            }
            return data;
        }

        public boolean isSameSet(int a, int b) {
            int parentA = parent[a];
            int parentB = parent[b];
            return parentA == parentB;
        }

        public void union(int a, int b) {
            int parentA = parent[a];
            int parentB = parent[b];
            if (parentA != parentB) {
                int sizeA = sizes[a];
                int sizeB = sizes[b];
                int shortNum = sizeA < sizeB ? parentA : parentB;
                int longNum = shortNum == parentA ? parentB : parentA;
                parent[shortNum] = longNum;
                sizes[longNum] += sizes[shortNum];
                size--;
            }
        }
    }

    public static int job(int[][] data){
        int N = data.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {//只需要循环一半矩阵即可
                if (data[i][j]==1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.size;
    }

}
