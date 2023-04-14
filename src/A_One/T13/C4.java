package A_One.T13;

/**
 * 规定1和A对应、2和B对应、3和C对应。。。。
 * 那么一个数字字符串比如“1111”就可以转化为“AAA”、“KA”、“AK”
 * 给定一个只有数字字符组成的字符串str，返回多少种转化结果。
 */
public class C4 {

    public static int jobOne(String data) {
        if (data == null || data.length() == 0) {
            return 0;
        }
        char[] chars = data.toCharArray();
        return process(chars, 0);
    }

    public static int process(char[] chars, int index) {
        if (chars.length == index) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }
        int p1 = process(chars, index + 1);
        if (index+1<chars.length && (chars[index]-'0')*10+(chars[index+1]-'0')<27){
            p1 += process(chars, index+2);
        }
        return p1;
    }

    public static int jobTwo(String data){
        if (data == null || data.length() == 0){
            return 0;
        }
        char[] chars = data.toCharArray();
        int n = chars.length;
        int[] dpMap = new int[n+1];
        dpMap[n] = 1;
        for (int i = n-1; i >= 0; i--) {
            if (chars[i] != '0'){
                int p1 = dpMap[i+1];
                if (i+1<chars.length && (chars[i]-'0')*10+(chars[i+1]-'0')<27){
                    p1 += dpMap[i+2];
                }
                dpMap[i] = p1;
            }
        }
        return dpMap[0];
    }

    public static void main(String[] args) {
        String data = "1111";
        System.out.println(jobOne(data));
        System.out.println(jobTwo(data));
    }

}
