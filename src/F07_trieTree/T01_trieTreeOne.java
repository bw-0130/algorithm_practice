package F07_trieTree;

/**
 * ## 前缀树
 * insert() 插入
 * serach() 查询
 * delete() 删除
 * prefixSerach() 查询前缀
 */
public class T01_trieTreeOne {

    //只适用字符串为字母
    public static class Node {
        public int pass;//开始计数
        public int end;//结束计数
        public Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];//26个字母
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
            char[] chars = data.toCharArray();
            Node cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
                cur.pass++;
            }
            cur.end++;
        }

        //查找单词出现过几次
        public int search(String data) {
            if (data == null) {
                return 0;
            }
            Node node = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i]-'a';
                if (node.nexts[index]==null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public void delete(String data){
            if (search(data) != 0){
                Node node = root;
                node.pass--;
                char[] chars = data.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i]-'a';
                    if (--node.nexts[index].pass == 0){//优化点
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        public int prefixSerach(String data){
            if (data == null){
                return 0;
            }
            Node node = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts[index]==null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert("abc");
        tree.insert("abd");
        tree.insert("abdc");
        System.out.println(tree.search("abc"));
        System.out.println(tree.prefixSerach("ab"));
        System.out.println(tree.prefixSerach("abd"));
        tree.delete("abd");
        System.out.println(tree.prefixSerach("abd"));
    }

}
