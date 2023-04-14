package A_One.T12;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部序列
 */
public class C3 {

    public static List<String> job(String data) {
        List<String> res = new ArrayList<>();
        if (data == null || data.length() == 0) {
            return res;
        }
        char[] chars = data.toCharArray();
        List<Character> characters = new ArrayList<>();
        String temp = "";
        for (char str : chars) {
            characters.add(str);
        }
        process(characters, res, temp);
        return res;
    }

    public static void process(List<Character> characters, List<String> res, String temp) {
        if (characters.isEmpty()) {
            res.add(temp);
            return;
        }
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            characters.remove(character);
            process(characters, res, temp + character);
            characters.add(i, character);
        }
    }

    public static void main(String[] args) {
        String data = "abc";
        List<String> job = job(data);
        for (String str : job) {
            System.out.println(str);
        }
    }

}
