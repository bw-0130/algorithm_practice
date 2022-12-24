package F12_violenceRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 */
public class C02_PrintAllSubsquences {

    public static List<String> print(String data) {
        List<String> res = new ArrayList<>();
        if (data == null || data.length() == 0) {
            return res;
        }
        String temp = "";
        char[] chars = data.toCharArray();
        process(chars, 0, temp, res);
        return res;
    }

    public static void process(char[] chars, int index, String temp, List<String> res) {
        if (chars.length == index) {
            res.add(temp);
            return;
        }
        process(chars, index + 1, temp, res);
        process(chars, index + 1, temp + String.valueOf(chars[index]), res);
    }

    public static void main(String[] args) {
        String data= "abc";
        List<String> print = print(data);
        for (String str:print){
            System.out.println(str);
        }
    }

}
