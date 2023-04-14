package A_One.T12;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部序列,要求不要出现重复的排列
 */
public class C4 {

    public static List<String> job(String data){
        List<String> res = new ArrayList<>();
        if (data == null || data.length() == 0){
            return res;
        }
        char[] chars = data.toCharArray();
        process(chars, res, 0);
        return res;
    }

    public static void process(char[] chars, List<String> res, int index){
        if (chars.length == index){
            res.add(String.valueOf(chars));
            return;
        }
        boolean[] booleans = new boolean[256];
        for (int i = index; i < chars.length; i++) {
            if (!booleans[chars[i]]){
                booleans[chars[i]] = true;
                swap(chars, index, i);
                process(chars, res, index+1);
                swap(chars, index, i);//还原现场
            }
        }
    }

    public static void swap(char[] chars, int a, int b){
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void main(String[] args) {
        String data = "aab";
        List<String> job = job(data);
        for (String str : job){
            System.out.println(str);
        }
    }

}
