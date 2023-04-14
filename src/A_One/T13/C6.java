package A_One.T13;

/**
 * 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如:"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class C6 {

    public static int jobOne(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return 0;
        }
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        return process(charsA, charsB, charsA.length - 1, charsB.length - 1);
    }

    public static int process(char[] charsA, char[] charsB, int aSize, int bSize) {
        if (aSize == 0 && bSize == 0) {
            return charsA[aSize] == charsB[bSize] ? 1 : 0;
        }
        if (aSize == 0) {
            if (charsA[aSize] == charsB[bSize]) {
                return 1;
            }
            return process(charsA, charsB, aSize, bSize - 1);
        }
        if (bSize == 0) {
            if (charsA[aSize] == charsB[bSize]) {
                return 1;
            }
            return process(charsA, charsB, aSize - 1, bSize);
        }
        int p1 = process(charsA, charsB, aSize - 1, bSize);
        int p2 = process(charsA, charsB, aSize, bSize - 1);
        int p3 = charsA[aSize] == charsB[bSize] ? (1 + process(charsA, charsB, aSize - 1, bSize - 1)) : 0;
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int jobTwo(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return 0;
        }
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        int aSize = charsA.length;
        int bSize = charsB.length;
        int[][] dpMap = new int[aSize][bSize];
        dpMap[0][0] = charsA[0] == charsB[0] ? 1 : 0;
        for (int i = 1; i < bSize; i++) {
            if (charsA[0] == charsB[i]){
                dpMap[0][i] = 1;
            }else {
                dpMap[0][i] = dpMap[0][i-1];
            }
        }
        for (int i = 1; i < aSize; i++) {
            if (charsA[i] == charsB[0]){
                dpMap[i][0] = 1;
            }else {
                dpMap[i][0] = dpMap[i-1][0];
            }
        }
        for (int i = 1; i < aSize; i++) {
            for (int j = 1; j < bSize; j++) {
                int p1 = dpMap[i-1][j];
                int p2 = dpMap[i][j-1];
                int p3 = charsA[i] == charsB[j] ? (1 + dpMap[i-1][j-1]) : 0;
                dpMap[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dpMap[aSize-1][bSize-1];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "artbgcymdpe";
        System.out.println(jobOne(text1, text2));
        System.out.println(jobTwo(text1, text2));
    }

}
