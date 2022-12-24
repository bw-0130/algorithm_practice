import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        //list.forEach(a-> System.out.println(a));

        Stream listS = list.stream();
        Stream a = listS.filter(s -> !s.equals("a"));
        //a.forEach(s-> System.out.println(s));
        List collect = (List)a.collect(Collectors.toList());
        collect.forEach(s-> System.out.println(s));
    }
}
