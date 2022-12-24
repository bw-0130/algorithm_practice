package F13_dynamicPlanning;

import java.util.HashMap;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文，arr每一个字符串代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str=“babac”，arr={"ba","c","abcd"}
 * 至少需要两张贴纸“ba”和“abcd”，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a，2个b，1个c。是可以拼出str的所以返回2。
 */
public class C05_StickersToSpellWord {

    //暴力递归方法
    public static int jobOne(String[] arr, String str) {
        if (arr == null || arr.length == 0 || str == null || str.length() == 0) {
            return 0;
        }
        return processOne(arr, str);
    }

    public static int processOne(String[] arr, String rest) {
        if (rest.length() == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            String s = minus(rest, arr[i]);
            if (s.length() != rest.length()) {
                res = Math.min(res, processOne(arr, s));
            }
        }
        return res + (res == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char cha : str1) {
            count[cha - 'a']++;
        }
        for (char cha : str2) {
            count[cha - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    //优化一，用词频代替贴纸数组
    public static int jobTwo(String[] arr, String str) {
        if (arr == null || arr.length == 0 || str == null || str.length() == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] countArr = new int[N][26];
        for (int i = 0; i < N; i++) {
            String data = arr[i];
            char[] chars = data.toCharArray();
            for (char ch : chars) {
                countArr[i][ch - 'a']++;
            }
        }
        return processTwo(countArr, str);
    }

    public static int processTwo(int[][] countArr, String rest) {
        if (rest.length() == 0) {
            return 0;
        }
        int[] countRest = new int[26];
        char[] chars = rest.toCharArray();
        for (char ch : chars) {
            countRest[ch - 'a']++;
        }
        int N = countArr.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] arr = countArr[i];
            //剪纸技巧
            if (arr[chars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (countRest[j]>0) {
                       int num = countRest[j]- arr[j];
                        for (int k = 0; k < num; k++) {
                            builder.append((char)(j+'a'));
                        }
                    }
                }
                res = Math.min(res, processTwo(countArr, builder.toString()));
            }
        }
        return res + (res == Integer.MAX_VALUE?0:1);
    }

    //优化二，根据用词频代替数组后加入傻缓存
    public static int jobThree(String[] arr, String str){
        if (arr == null || arr.length == 0 || str == null || str.length() == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] arrCount = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = arr[i].toCharArray();
            for (char ch : chars){
                arrCount[i][ch-'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        return processThree(arrCount, str, dp);
    }

    public static int processThree(int[][] arrCount, String rest, HashMap<String, Integer> dp){
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }
        if (rest.length() == 0){
            return 0;
        }
        int[] restCount = new int[26];
        char[] chars = rest.toCharArray();
        for (char ch : chars){
            restCount[ch-'a']++;
        }
        int N = arrCount.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] arr = arrCount[i];
            if (arr[chars[0]-'a']>0){
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    int num = restCount[j]-arr[j];
                    for (int k = 0; k < num; k++) {
                        builder.append((char)(j+'a'));
                    }
                }
                res = Math.min(res, processThree(arrCount, builder.toString(), dp));
            }
        }
        int ans = res + (res == Integer.MAX_VALUE ? 0 : 1);
        dp.put(rest, ans);
        return ans;
    }

    public static void main(String[] args) {
        String str = "babac";
        String[] arr = {"ba", "c", "abcd"};
        System.out.println(jobOne(arr, str));
        System.out.println(jobTwo(arr, str));
        System.out.println(jobThree(arr, str));
    }

}
