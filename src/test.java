import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.forEach(a ->System.out.print(a+" "));
        System.out.println();
        list.set(1, 12);
        list.forEach(a ->System.out.print(a+" "));
    }

}
