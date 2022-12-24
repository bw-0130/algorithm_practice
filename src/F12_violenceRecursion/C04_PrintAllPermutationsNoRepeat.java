package F12_violenceRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部序列,要求不要出现重复的排列
 */
public class C04_PrintAllPermutationsNoRepeat {

    public static List<String> print(String data){
        List<String> res = new ArrayList<>();
        if (data == null || data.length() == 0){
            return res;
        }
        char[] chars = data.toCharArray();
        process(chars,0,res);
        return res;
    }

    public static void process(char[] chars, int index, List<String> res){
        if (chars.length == index){
            res.add(String.valueOf(chars));
            return;
        }
        boolean[] booleans = new boolean[256];//ASCII表一共256个
        for (int i = index; i < chars.length; i++) {
            if (!booleans[chars[i]]){
                booleans[chars[i]] = true;
                swap(chars, index, i);
                process(chars, index+1, res);
                swap(chars, index, i);
            }
        }
    }

    public static void swap(char[] chars, int a, int b){
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void main(String[] args) {
        String data = "abb";
        List<String> print = print(data);
        for (String str : print){
            System.out.println(str);
        }
    }

}
