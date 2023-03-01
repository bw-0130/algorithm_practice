package A_One.T01;

import javax.sound.midi.Soundbank;

/**
 * 不使用额外变量交换两个数的值
 * 注：int类型为值传递不是引用传递
 * 24/01/2023 bianwei
 */
public class C1 {
    public static void swap(int a, int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a+"~"+b);
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 8;
        swap(a, b);
        System.out.println(a+" "+b);
    }

}