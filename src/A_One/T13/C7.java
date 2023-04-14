package A_One.T13;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如：str=“a123b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者123c321，返回长度7
 */
public class C7 {

    public static int jobOne(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0, chars.length - 1);
    }

    public static int process(char[] chars, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return chars[l] == chars[r] ? 2 : 0;
        }
        int p1 = process(chars, l + 1, r);
        int p2 = process(chars, l, r - 1);
        int p3 = process(chars, l + 1, r - 1);
        int p4 = chars[l] != chars[r] ? 0 : (2 + process(chars, l + 1, r - 1));
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int jobTwo(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int n = chars.length;
        int[][] dpMap = new int[n][n];
        dpMap[n-1][n-1] = 1;
        for (int i = 0; i < n-1; i++) {
            dpMap[i][i] = 1;
            dpMap[i][i+1] = chars[i] == chars[i+1] ? 2 : 0;
        }
        for (int i = n-3; i >= 0; i--) {
            for (int j = i+2; j < n; j++) {
                int p1 = dpMap[i+1][j];
                int p2 = dpMap[i][j-1];
                int p3 = dpMap[i+1][j-1];
                int p4 = chars[i] != chars[j] ? 0 : (2 + dpMap[i+1][j-1]);
                dpMap[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dpMap[0][n-1];
    }

    public static void main(String[] args) {
        String str = "a123b3c43def2ghi1kpm";
        System.out.println(jobOne(str));
        System.out.println(jobTwo(str));
    }

}
