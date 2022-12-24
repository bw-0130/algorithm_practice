package F18_manacher;

/**
 * 一个字符串str只能在它后面添加字符，想要将str整体都变成回文串至少要添加几个字符。
 * 思路：找到包含最后一个字符的回文子串，把其他字符逆序后添加到尾部即，其他字符数量就是要返回的字符数。
 */
public class C02_AddShortestEnd {

    public static char[] getManacherStr(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    public static int manacherJob(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] manacherStr = getManacherStr(str);
        int[] pArr = new int[manacherStr.length];
        int R = -1;
        int C = -1;
        int endPArr = -1;
        for (int i = 0; i < manacherStr.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < manacherStr.length && i - pArr[i] > -1){
                if (manacherStr[i + pArr[i]] == manacherStr[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (i+pArr[i]>R){
                R = i+pArr[i];
                C = i;
            }
            if (R == manacherStr.length){
                endPArr = pArr[i];
                break;
            }
        }
        int res = str.length()-(endPArr-1);
        char[] printRes = new char[res];
        for (int i = 0; i < printRes.length; i++) {
            printRes[printRes.length-i-1] = manacherStr[2*i+1];
        }
        System.out.println(String.valueOf(printRes));
        return res;
    }

    public static void main(String[] args) {
        String str = "abcd123321";
        System.out.println(manacherJob(str));
    }

}
