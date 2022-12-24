package F22_indexTree_AC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 */
public class C03_AC {

    //前缀树节点
    public static class Node {
        public String end;//是结尾接节点则是改字符串，不是则为null
        public boolean endUse;//表示end是否加入过答案
        public Node fail;
        public Node[] next;

        public Node() {
            end = null;
            endUse = false;
            fail = null;
            next = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;//根节点

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String data) {
            char[] chars = data.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = data;
        }

        //链接fail指针，按层遍历
        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();//弹出队列
                for (int i = 0; i < 26; i++) {
                    if (cur.next[i] != null) {
                        cur.next[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.next[i] != null) {
                                cur.next[i].fail = cfail.next[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.next[i]);
                    }
                }
            }
        }

        //传入大文章找前缀树关键字关键字
        public List<String> containWords(String content) {
            List<String> res = new ArrayList<>();
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur.next[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.next[index] != null ? cur.next[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUse){
                        break;
                    }
                    if (follow.end != null){
                        res.add(follow.end);
                        follow.endUse = true;
                    }
                    follow = follow.fail;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        ac.insert("bianwei");
        ac.build();
        List<String> res = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

}
