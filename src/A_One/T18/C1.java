package A_One.T18;

/**
 * manacher算法本身：求一个字符串最长回文子序列的长度
 */
public class C1 {

    public static char[] getManacherArray(String str) {
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
        char[] manacherArray = getManacherArray(str);
        int[] pArr = new int[manacherArray.length];//回文半径数组
        int R = -1;
        int C = -1;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < manacherArray.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[C * 2 - i], R - i) : 1;
            while (i+pArr[i]<manacherArray.length && i-pArr[i]>-1){
                if (manacherArray[i+pArr[i]] == manacherArray[i-pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (i+pArr[i]>R){
                R = i+pArr[i];
                C = i;
            }
            res = Math.max(res,pArr[i]);
        }
        return res-1;
    }

    public static void main(String[] args) {
        String str = "abcd123321";
        System.out.println(job(str));
    }

}
