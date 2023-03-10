package F11_greedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 */
public class T01_lowestLexicography {

    //递归方法
    public static String jobOne(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        TreeSet<String> res = processOne(strs);
        return res.size() == 0 ? "" : res.first();
    }

    public static TreeSet<String> processOne(String[] strs) {
        TreeSet<String> treeSet = new TreeSet<>();
        if (strs.length == 0) {
            treeSet.add("");
            return treeSet;
        }
        for (int i = 0; i < strs.length; i++) {
            String firstString = strs[i];
            String[] newStrs = removeStrs(strs, i);
            TreeSet<String> treeSet1 = processOne(newStrs);
            for (String data : treeSet1) {
                treeSet.add(firstString + data);
            }
        }
        return treeSet;
    }

    public static String[] removeStrs(String[] strs, int index) {
        int len = strs.length;
        String[] newStrs = new String[len - 1];
        int a = 0;
        for (int i = 0; i < len; i++) {
            if (i != index) {
                newStrs[a++] = strs[i];
            }
        }
        return newStrs;
    }

    //贪心算法
    public static String jobTwo(String[] strs){
        if (strs == null || strs.length < 1) {
            return "";
        }
        Arrays.sort(strs, new myComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }
    public static class myComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static void main(String[] args) {
        String[] data = new String[]{"b","a","c","d"};
        System.out.println(jobOne(data));
        System.out.println(jobTwo(data));
    }

}
