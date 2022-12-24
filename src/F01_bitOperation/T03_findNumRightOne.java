package F01_bitOperation;

/**
 * 怎么把一个int类型的数，提取出最右侧的1来
 */
public class T03_findNumRightOne {

    public static int find(int data){
        int temp = ~data +1;
        System.out.println(temp);
        int res = data & temp;
        return res;
    }

    public static void main(String[] args) {
        int data = 3;
        System.out.println(find(data));
    }

}
