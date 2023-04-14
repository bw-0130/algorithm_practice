package A_One.T11;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X'和‘.‘两种字符构成。
 * ’X'表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1,i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class C5 {

    public static int jobOne(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }
        return process(str, 0, new HashSet<>());
    }

    public static int process(char[] str, int index, HashSet<Integer> set) {
        if (str.length == index) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {
                    if (!set.contains(i - 1) && !set.contains(i) && !set.contains(i + 1)) {
                        return Integer.MAX_VALUE;//无效值
                    }
                }
            }
            return set.size();
        }
        int noLight = process(str, index + 1, set);
        int haveLight = Integer.MAX_VALUE;
        if (str[index] == '.') {
            set.add(index);
            haveLight = process(str, index + 1, set);
            set.remove(index);
        }
        return Math.min(noLight, haveLight);
    }

    public static int jobTwo(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }
        int index = 0;
        int res = 0;
        while (index <= str.length) {
            if (str[index] == 'X'){
                index++;
            }else {
                res++;
                if (str[index+1]=='X'){
                    index = index+2;
                }else {
                    index = index+3;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String data = "..X...X..";
        System.out.println(jobOne(data.toCharArray()));
        System.out.println(jobTwo(data.toCharArray()));
    }

}
