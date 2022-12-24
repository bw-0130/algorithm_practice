public class test {

    public static void main(String[] args) {
        String data = "奥/鹏教\\育\\";
        System.out.println(data);
        data = data.replaceAll("/","-");
        data = data.replaceAll("\\\\","-");
        System.out.println(data);
        int str  = 1==1?3:4*2;
        System.out.println(str);
    }

}
