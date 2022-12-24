package F12_violenceRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部序列
 */
public class C03_PrintAllPermutations {

    public static List<String> print(String data){
        List<String> res = new ArrayList<>();
        if (data == null || data.length() == 0){
            return res;
        }
        char[] chars = data.toCharArray();
        String temp = "";
        List<Character> chars2 = new ArrayList<>();
        for (char c : chars){
            chars2.add(c);
        }
        process(chars2, temp, res);
        return res;
    }

    public static void process(List<Character> chars, String temp, List<String> res){
        if (chars.isEmpty()){
            res.add(temp);
            return;
        }
        for (int i = 0; i < chars.size(); i++) {
            char aChar = chars.get(i);
            chars.remove(i);
            process(chars, temp+String.valueOf(aChar), res);
            chars.add(i,aChar);
        }
    }

    public static void main(String[] args) {
        String data= "abc";
        List<String> print = print(data);
        for (String str:print){
            System.out.println(str);
        }
    }

}
