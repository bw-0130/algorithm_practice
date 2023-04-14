package A_One.T13;

import java.util.HashMap;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文，arr每一个字符串代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str=“babac”，arr={"ba","c","abcd"}
 * 至少需要两张贴纸“ba”和“abcd”，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a，2个b，1个c。是可以拼出str的所以返回2。
 */
public class C5 {

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
        int num = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            String minus = minus(rest, arr[i]);
            if (minus.length() != rest.length()) {
                num = Math.min(num, processOne(arr, minus));
            }
        }
        return num + (num == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String rest, String str) {
        int[] countMap = new int[26];
        char[] rests = rest.toCharArray();
        char[] strs = str.toCharArray();
        for (char data : rests) {
            countMap[data - 'a']++;
        }
        for (char data : strs) {
            countMap[data - 'a']--;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < countMap.length; i++) {
            if (countMap[i] > 0) {
                for (int j = 0; j < countMap[i]; j++) {
                    res.append((char) (i + 'a'));
                }
            }
        }
        return res.toString();
    }

    public static int jobTwo(String[] arr, String str) {
        if (arr == null || arr.length == 0 || str == null || str.length() == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] arrCount = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = arr[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                arrCount[i][chars[j] - 'a']++;
            }
        }
        return processTwo(arrCount, str);
    }

    public static int processTwo(int[][] arrCount, String rest) {
        if (rest.length() == 0) {
            return 0;
        }
        int[] restCount = new int[26];
        char[] chars = rest.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            restCount[chars[i] - 'a']++;
        }
        int N = arrCount.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] arrs = arrCount[i];
            if (arrs[chars[0] - 'a'] > 0) {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (restCount[j] > 0) {
                        int num = restCount[j] - arrs[j];
                        for (int k = 0; k < num; k++) {
                            str.append((char) (j + 'a'));
                        }
                    }
                }
                res = Math.min(res, processTwo(arrCount, str.toString()));
            }
        }
        return res + (res == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int jobThree(String[] arr, String str) {
        if (arr == null || arr.length == 0 || str == null || str.length() == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] arrCount = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = arr[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                arrCount[i][chars[j] - 'a']++;
            }
        }
        HashMap<String, Integer> dpMap = new HashMap<>();
        return processThree(arrCount, str, dpMap);
    }

    public static int processThree(int[][] arrCount, String rest, HashMap<String, Integer> dpMap) {
        if (dpMap.containsKey(rest)) {
            return dpMap.get(rest);
        }
        if (rest.length() == 0) {
            return 0;
        }
        int[] restCount = new int[26];
        char[] chars = rest.toCharArray();
        for (char data : chars) {
            restCount[data - 'a']++;
        }
        int N = arrCount.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] arr = arrCount[i];
            if (arr[chars[0] - 'a'] > 0) {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (restCount[j]>0){
                        int num = restCount[j]-arr[j];
                        for (int k = 0; k < num; k++) {
                            str.append((char)(j+'a'));
                        }
                    }
                }
                res = Math.min(res, processThree(arrCount, str.toString(), dpMap));
            }
        }
        int ans = res + (res == Integer.MAX_VALUE?0:1);
        dpMap.put(rest, ans);
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
