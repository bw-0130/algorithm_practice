package F12_violenceRecursion;

/**
 * 打印n层汉诺塔从左边到右边的全部过程
 */
public class C01_Hanoi {

    public static void print(int N) {
        if (N < 1) {
            return;
        }
        process(N, "left", "right", "mid");
    }

    public static void process(int N, String from, String to, String other){
        if (N == 1){
            System.out.println("move 1 from:"+from+" to:"+to);
        }else {
            process(N-1, from, other, to);
            System.out.println("move "+N+" from:"+from+" to:"+to);
            process(N-1, other, to, from);
        }
    }

    public static void main(String[] args) {
        print(3);
    }

}
