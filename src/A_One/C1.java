package A_One;

/**
 * ## 前缀树
 * insert() 插入
 * serach() 查询
 * delete() 删除
 * prefixSerach() 查询前缀
 */
public class C1 {

    public static class Node{
        public int pass;//开始计数
        public int end;//结束计数
        public Node[] nodes;

        public Node() {
            pass = 0;
            end = 0;
            nodes = new Node[26];//26个英文字母
        }
    }

    public static class Tree{
        public Node root;

        public Tree() {
            root = new Node();
        }

    }

}