package A_One.T09;

/**
 * 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕凸起的方向指向纸的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开此时又三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。请从上到下打印所有折痕的方向。
 * 例如：N=1时，打印：down； N = 2时，打印：down  down up
 * 根据规律是二叉树中序遍历
 */
public class C8 {

    public static void job(int N) {
        process(1, N, true);
    }

    public static void process(int start, int num, boolean flag) {
        if (start > num) {
            return;
        }
        process(start + 1, num, true);
        System.out.println(flag ? "凹" : "凸");
        process(start + 1, num, false);
    }

    public static void main(String[] args) {
        job(3);
    }

}
