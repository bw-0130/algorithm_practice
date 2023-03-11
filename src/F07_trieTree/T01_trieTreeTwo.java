package F07_trieTree;

import java.util.HashMap;

/**
 * 前缀树
 * insert() 插入
 * serach() 查询
 * delete() 删除
 * prefixSerach() 查询前缀
 */
public class T01_trieTreeTwo {

    public static class Node{
        public int pass;
        public int end;
        public HashMap<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Tree{
        public Node root;

        public Tree() {
            root = new Node();
        }

        public void insert(String data){
            if (data == null){
                return;
            }
            Node node = root;
            node.pass++;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (!node.nexts.containsKey(index)){
                    node.nexts.put(index, new Node());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public int search(String data){
            if (data == null){
                return 0;
            }
            Node node = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (!node.nexts.containsKey(index)){
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public void delete(String data){
            if (search(data)!=0){
                Node node = root;
                node.pass--;
                char[] chars = data.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i]-'a';
                    if (--node.nexts.get(index).pass == 0){
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
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
                if (!node.nexts.containsKey(index)){
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        T01_trieTreeOne.Tree tree = new T01_trieTreeOne.Tree();
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

