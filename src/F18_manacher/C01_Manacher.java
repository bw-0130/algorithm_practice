package F18_manacher;

/**
 * manacher算法本身：求一个字符串最长回文子序列的长度
 */
public class C01_Manacher {

    //获取一个manacher串
    public static char[] getManacherString(String str) {
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
        char[] manacherString = getManacherString(str);
        int[] pArr = new int[manacherString.length];//回文半径
        int R = -1;//扩到最右的下一个失败位置
        int C = -1;
        int maxP = Integer.MIN_VALUE;
        for (int i = 0; i < manacherString.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < manacherString.length && i - pArr[i] > -1) {
                if (manacherString[i + pArr[i]] == manacherString[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (i+pArr[i]>R){
                R = i+pArr[i];
                C = i;
            }
            maxP = Math.max(maxP, pArr[i]);
        }
        return maxP-1;
    }

    public static void main(String[] args) {
        String str = "abcd123321";
        System.out.println(manacherJob(str));
    }

}
