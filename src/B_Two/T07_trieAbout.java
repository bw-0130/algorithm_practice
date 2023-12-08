package B_Two;

import java.util.HashMap;

/*前缀树相关*/
public class T07_trieAbout {

    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] next;

        public Node1() {
            pass = 0;
            end = 0;
            next = new Node1[26];//因为26个字母
        }
    }

    public static class Node2{
        public int pass;
        public int end;
        public HashMap<Integer, Node2> next;

        public Node2() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static void main(String[] args) {
       /* tridOne tree1 = new tridOne();
        tree1.insert("abc");
        tree1.insert("abd");
        tree1.insert("abdc");
        System.out.println(tree1.search("abc"));
        System.out.println(tree1.prefixSerach("ab"));
        System.out.println(tree1.prefixSerach("abd"));
        tree1.delete("abd");
        System.out.println(tree1.prefixSerach("abd"));*/
       /*---------------------------------------------------------*/
        tridTwo tree2 = new tridTwo();
        tree2.insert("abc");
        tree2.insert("abd");
        tree2.insert("abdc");
        System.out.println(tree2.search("abc"));
        System.out.println(tree2.prefixSerach("ab"));
        System.out.println(tree2.prefixSerach("abd"));
        tree2.delete("abd");
        System.out.println(tree2.prefixSerach("abd"));
    }

    public static class tridOne {
        private Node1 root;

        public tridOne() {
            root = new Node1();
        }

        public void insert(String data) {
            if (data == null) {
                return;
            }
            char[] chars = data.toCharArray();
            Node1 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i]-'a';
                if (cur.next[index]==null){
                    cur.next[index] = new Node1();
                }
                cur = cur.next[index];
                cur.pass++;
            }
            cur.end++;
        }
        
        public int search(String data){
            if (data == null){
                return 0;
            }
            char[] chars = data.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.next[index]==null){
                    return 0;
                }
                cur = cur.next[index];
            }
            return cur.end;
        }
        
        public void delete(String data){
            if (search(data) != 0){
                char[] chars = data.toCharArray();
                Node1 cur = root;
                cur.pass--;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (--cur.next[index].pass == 0){
                        cur.next[index] = null;
                        return;
                    }
                    cur = cur.next[index];
                }
                cur.end--;
            }
        }
        
        public int prefixSerach(String data){
            if (data == null){
                return 0;
            }
            Node1 cur = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.next[index] == null){
                    return 0;
                }
                cur = cur.next[index];
            }
            return cur.pass;
        }
    }

    public static class tridTwo{
        private Node2 root;

        public tridTwo() {
            root = new Node2();
        }

        public void insert(String data){
            if (data == null){
                return;
            }
            char[] chars = data.toCharArray();
            Node2 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (!cur.next.containsKey(index)){
                    cur.next.put(index, new Node2());
                }
                cur = cur.next.get(index);
                cur.pass++;
            }
            cur.end++;
        }

        public int search(String data){
            if (data == null){
                return 0;
            }
            Node2 cur = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (!cur.next.containsKey(index)){
                    return 0;
                }
                cur = cur.next.get(index);
            }
            return cur.end;
        }

        public void delete(String data){
            if (search(data) != 0){
                char[] chars = data.toCharArray();
                Node2 cur = root;
                cur.pass--;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (--cur.next.get(index).pass == 0){
                        cur.next.remove(index);
                        return;
                    }
                    cur = cur.next.get(index);
                }
                cur.end--;
            }
        }

        public int prefixSerach(String data){
            if (data == null){
                return 0;
            }
            Node2 cur = root;
            char[] chars = data.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (!cur.next.containsKey(index)){
                    return 0;
                }
                cur = cur.next.get(index);
            }
            return cur.pass;
        }
    }

}
