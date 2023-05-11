package A_One.T18;

/**
 * 一个字符串str只能在它后面添加字符，想要将str整体都变成回文串至少要添加几个字符。
 * 思路：找到包含最后一个字符的回文子串，把其他字符逆序后添加到尾部即，其他字符数量就是要返回的字符数。
 */
public class C2 {

    public static char[] getManacher(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    public static int job(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        char[] manacher = getManacher(str);
        int[] pArr = new int[manacher.length];
        int R = -1;
        int C = -1;
        int endPArr = -1;
        for (int i = 0; i < manacher.length; i++) {
            pArr[i] = R > i ? Math.max(pArr[C * 2 - i], R - i) : 1;
            while (i+pArr[i]<manacher.length && i-pArr[i]>-1){
                if (manacher[i+pArr[i]] == manacher[i-pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (i+pArr[i]>R){
                R = i+pArr[i];
                C = i;
            }
            if (R == manacher.length){
                endPArr = pArr[i];
                break;
            }
        }
        int res = str.length()-(endPArr-1);
        char[] print = new char[res];
        for (int i = 0; i < print.length; i++) {
            print[print.length-i-1] = manacher[i*2+1];
        }
        System.out.println(String.valueOf(print));
        return res;
    }

    public static void main(String[] args) {
        String str = "abcd123321";
        System.out.println(job(str));
    }

}
