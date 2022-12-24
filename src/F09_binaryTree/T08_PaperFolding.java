package F09_binaryTree;

/**
 * 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕凸起的方向指向纸的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开此时又三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。请从上到下打印所有折痕的方向。
 * 例如：N=1时，打印：down； N = 2时，打印：down  down up
 * 根据规律是二叉树中序遍历
 */
public class T08_PaperFolding {

    public static void print(int n){
        process(1, n, true);
    }

    /**
     * 中序遍历
     * @param i 当前层数
     * @param N 一共多少层
     * @param flag  true:凹  false: 凸
     */
    public static void process(int i, int N, boolean flag){
        if (i>N){
            return;
        }
        process(i+1,N, true);
        System.out.print(flag?"凹 ":"凸 ");
        process(i+1,N, false);
    }

    public static void main(String[] args) {
        print(2);
        System.out.println();
        print(3);
    }

}
