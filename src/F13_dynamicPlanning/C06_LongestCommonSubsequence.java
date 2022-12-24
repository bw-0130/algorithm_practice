package F13_dynamicPlanning;

/**
 * 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如:"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class C06_LongestCommonSubsequence {

    //暴力递归方法
    public static int jobOne(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();
        return process(a, b, a.length - 1, b.length - 1);
    }

    public static int process(char[] a, char[] b, int i, int j) {
        if (i == 0 && j == 0) {
            return a[i] == b[j] ? 1 : 0;
        }
        if (i == 0) {
            if (a[i] == b[j]) {
                return 1;
            }
            return process(a, b, i, j - 1);
        }
        if (j == 0) {
            if (a[i] == b[j]) {
                return 1;
            }
            return process(a, b, i - 1, j);
        }
        int p1 = process(a, b, i, j - 1);
        int p2 = process(a, b, i - 1, j);
        int p3 = a[i] == b[j] ? (1 + process(a, b, i - 1, j - 1)) : 0;
        return Math.max(p1, Math.max(p2, p3));
    }

    //动态规划方法
    public static int jobTwo(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();
        int aLength = a.length;
        int bLength = b.length;
        int[][] dpMap = new int[aLength][bLength];
        dpMap[0][0] = a[0] == b[0] ? 1 : 0;
        for (int j = 1; j < bLength; j++) {
            if (a[0] == b[j]){
                dpMap[0][j] = 1;
            }else {
                dpMap[0][j] = dpMap[0][j-1];
            }
        }
        for (int i = 1; i < aLength; i++) {
            if (b[0] == a[i]){
                dpMap[i][0] = 1;
            }else {
                dpMap[i][0] =  dpMap[i - 1][0];
            }
        }
        for (int i = 1; i < aLength; i++) {
            for (int j = 1; j < bLength; j++) {
                int p1 = dpMap[i] [j - 1];
                int p2 = dpMap[i - 1][j];
                int p3 = a[i] == b[j] ? (1 + dpMap[i - 1] [j - 1]) : 0;
                dpMap[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dpMap[aLength-1][bLength-1];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "artbgcymdpe";
        System.out.println(jobOne(text1, text2));
        System.out.println(jobTwo(text1, text2));
    }

}
