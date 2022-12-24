public class Singleton {

    private static volatile Singleton test = null;

    private Singleton() {
    }

    public static Singleton getInstance(){
        if (test == null){
            synchronized (Singleton.class){
                if (test == null){
                    test = new Singleton();
                }
            }
        }
        return test;
    }
}
