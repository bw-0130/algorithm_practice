package F17_KMP;

public class C01_KMP {

    public static int job(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int x = 0;
        int y = 0;
        int[] nextArray = getNextArray(chars2);
        while (x < chars1.length && y < chars2.length) {
            if (chars1[x] == chars2[y]) {
                x++;
                y++;
            } else if (nextArray[y] == -1) {
                x++;
            } else {
                y = nextArray[y];
            }
        }
        return y == chars2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] chars) {
        if (chars.length == 1) {
            return new int[]{-1};
        }
        int[] nextArray = new int[chars.length];
        nextArray[0] = -1;
        nextArray[1] = 0;
        int cur = 2;
        int cn = 0;
        while (cur < chars.length){
            if (chars[cur-1] == chars[cn]){
                nextArray[cur++] = ++cn;
            }else if (cn > 0){
                cn = nextArray[cn];
            }else {
                nextArray[cur++] = 0;
            }
        }
        return nextArray;
    }

    public static void main(String[] args) {
        String str1 = "abnbianweityju";
        String str2 = "bianwei";
        System.out.println(job(str1, str2));
    }

}
