package A_One.T17;

/**
 * KMP算法
 */
public class C1 {

    //生产nextArray数组
    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] nextArray = new int[str.length];
        nextArray[0] = -1;
        nextArray[1] = 0;
        int cur = 2;
        int cn = 0;
        while (cur < str.length) {
            if (str[cur - 1] == str[cn]) {
                nextArray[cur++] = ++cn;
            } else if (cn > 0) {
                cn = nextArray[cn];
            } else {
                nextArray[cur++] = 0;
            }
        }
        return nextArray;
    }

    public static int KMP(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] nextArray = getNextArray(chars2);
        int x = 0;
        int y = 0;
        while (x < chars1.length && y < chars2.length) {
            if (chars1[x] == chars2[y]){
                x++;
                y++;
            }else if (nextArray[y] == -1){
                x++;
            }else {
                y = nextArray[y];
            }
        }
        return y == str2.length() ? x - y : -1;
    }

    public static void main(String[] args) {
        String str1 = "ghababacj2ababacsh";
        String str2 = "ababac";
        System.out.println(KMP(str1, str2));
    }

}
