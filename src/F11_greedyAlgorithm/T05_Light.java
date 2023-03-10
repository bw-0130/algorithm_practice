package F11_greedyAlgorithm;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X'和‘.‘两种字符构成。
 * ’X'表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1,i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class T05_Light {

    //递归方法
    public static int jobOne(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        return processOne(str.toCharArray(), 0, new HashSet<>());
    }

    /**
     * @param str 字符串数组
     * @param index 当前位置
     * @param set 放灯位置记录
     * @return
     */
    public static int processOne(char[] str, int index, HashSet<Integer> set){
        if (str.length == index){
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X'){
                    if (!set.contains(i-1) && !set.contains(i) && !set.contains(i+1)){
                        return Integer.MAX_VALUE;//无效结果
                    }
                }
            }
            return set.size();
        }
        int noLight = processOne(str, index+1, set);
        int yesLight = Integer.MAX_VALUE;
        if (str[index] == '.'){
            set.add(index);
            yesLight = processOne(str, index+1, set);
            set.remove(index);
        }
        return Math.min(noLight, yesLight);
    }

    //贪心算法
    public static int jobTwo(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int res = 0;
        while (index<chars.length){
            if (chars[index] == 'X'){
                index++;
            }else {
                res++;
                if (chars[index+1]=='X'){
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
        System.out.println(jobOne(data));
        System.out.println(jobTwo(data));
    }

}
