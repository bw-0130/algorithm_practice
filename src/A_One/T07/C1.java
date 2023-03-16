package A_One.T07;

/**
 * ## 前缀树
 * insert() 插入
 * serach() 查询
 * delete() 删除
 * prefixSerach() 查询前缀
 */
public class C1 {

    public static class Node {
        public int pass;//开始计数
        public int end;//结束计数
        public Node[] nodes;

        public Node() {
            pass = 0;
            end = 0;
            nodes = new Node[26];//26个英文字母
        }
    }

    public static class Tree {
        public Node root;

        public Tree() {
            root = new Node();
        }

        public void insert(String data) {
            if (data == null) {
                return;
            }
            Node cur = root;
            cur.pass++;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nodes[index] == null) {
                    cur.nodes[index] = new Node();
                }
                cur = cur.nodes[index];
                cur.pass++;
            }
            cur.end++;
        }

        public int search(String data) {
            if (data == null) {
                return 0;
            }
            Node cur = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nodes[index] == null) {
                    return 0;
                }
                cur = cur.nodes[index];
            }
            return cur.end;
        }

        public void remove(String data) {
            if (search(data) == 0) {
                return;
            }
            Node cur = root;
            cur.pass--;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--cur.nodes[index].pass == 0) {
                    cur.nodes[index] = null;
                    return;
                }
                cur = cur.nodes[index];
            }
            cur.end--;
        }

        public int prefixSerach(String data) {
            if (data == null) {
                return 0;
            }
            Node cur = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nodes[index] == null){
                    return 0;
                }
                cur = cur.nodes[index];
            }
            return cur.pass;
        }

    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert("abcd");
        tree.insert("abcf");
        tree.insert("abce");
        System.out.println(tree.search("abcd"));
        System.out.println(tree.prefixSerach("abc"));
        tree.remove("abcf");
        System.out.println(tree.prefixSerach("abc"));
        System.out.println(tree.search("abcf"));

    }

}