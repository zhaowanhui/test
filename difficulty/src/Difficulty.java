
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @Description TODO
 * @Author zhaowanhui
 * @Date 2019/10/3 20:09
 * @ClassName Difficulty
 */
public class Difficulty {
    public static void main(String[] args){
        //fibonacci();
        prime();
    }
    /**
     * @Description TODO 斐波那契数列 0 1 1 2 3 5 8 ...。前两位和等于下一位，求给定n的前n项
     * @Params
     * @Return void
     * @Author zhaowanhui
     * @Date 2019/10/11 13:08
     */
    public static void fibonacci() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arrs = new int[n];
        for (int i = 0; i < n; i++) {
            arrs[i] = i;
            if (i > 1) {
                arrs[i] = arrs[i - 1] + arrs[i - 2];
            }
        }
        Arrays.stream(arrs).forEach(s-> System.out.print(s+"  "));
    }

    /**
     * @Description TODO 输入一整数n，求小于这个数的所有质数。定义一个boolean类型数组，初始值默认false(定义为是质数),
     *                   从下标2开始，将其倍数是false就改为true。
     * @Params null
     * @Return
     * @Author zhaowanhui
     * @Date 2019/10/11 14:04
     */
    public static void prime() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[] booleans = new boolean[n];
        for (int i=2;i<booleans.length/i;i++){
            booleans[i] = booleans[i]? false:true;
        }
    }
}
