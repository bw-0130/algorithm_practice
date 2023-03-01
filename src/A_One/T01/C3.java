package A_One.T01;

/**
 * 怎么把一个int类型的数，提取出最右侧的1来
 * 24/01/2023 bianwei
 */
public class C3 {
    public static void job(int data){
        data = data & (~data + 1);
        System.out.println(data);
    }

    public static void main(String[] args) {
        job(8);
    }
}