package F08_unionFind;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class T02_friendCircles {

    public static class UnionFind{
        public int[] parent;//父子关系数组
        public int[] help;//辅助数组
        public int[] size;//集合大小
        public int num;//集合数量

        public UnionFind(int N) {
            parent = new int[N];
            help = new int[N];
            size = new int[N];
            num = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        //找到代表节点
        public int findFather(int i){
            int index = 0;
            while (i != parent[i]){
                help[index++] = i;
                i = parent[i];
            }
            for (int j = index; j >= 0 ; j--) {
                parent[help[j]] = i;
            }
            return i;
        }
        //判断两个元素是否在同一集合内
        public boolean isSameSet(int a, int b){
            return findFather(a) == findFather(b);
        }
        //把两个元素所在的集合合并
        public void union(int a, int b){
            int fatherA = findFather(a);
            int fatherB = findFather(b);
            int sizeA = size[fatherA];
            int sizeB = size[fatherB];
            if (fatherA != fatherB){
                if (sizeA<=sizeB){
                    parent[fatherA] = fatherB;
                    size[fatherB] += size[fatherA];
                }else {
                    parent[fatherB] = fatherA;
                    size[fatherA] += size[fatherB];
                }
                num--;
            }
        }
    }

    public static int job(int[][]  isConnected){
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (isConnected[i][j]==1){
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.num;
    }

}
