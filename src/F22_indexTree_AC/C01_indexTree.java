package F22_indexTree_AC;

/**
 *indexTree
 */
public class C01_indexTree {

    public static class indexTree{
        private int[] tree;
        private int N;

        //0位置弃而不用
        public indexTree(int size) {
            tree = new int[size+1];
            N = size;
        }

        public int sum(int index){
            int res = 0;
            while (index>0){
                res += tree[index];
                index -= index & (-index);
            }
            return res;
        }

        public void add(int index, int d){
            while (index <= N){
                tree[index] += d;
                index += index & (-index);
            }
        }
    }

    public static void main(String[] args) {
        indexTree tree = new indexTree(5);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 2);
        tree.add(4, 1);
        tree.add(5, 6);
        System.out.println(tree.sum(4));
    }

}
