import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args){
       /* int[] arr= {0,4,7,2,5,6,8,1,3};
        for(int j=0;j<3;j++) {
            for (int i = (arr.length - 1) / 2; i >= 1; i--) {
                int maxIndex = i * 2;
                if (arr.length - 1 == maxIndex && arr[maxIndex] > arr[i]) {
                    arr[i] = arr[i] ^ arr[maxIndex];
                    arr[maxIndex] = arr[i] ^ arr[maxIndex];
                    arr[i] = arr[i] ^ arr[maxIndex];
                }
                if (arr.length - 1 != maxIndex) {
                    if (arr[maxIndex + 1] > arr[maxIndex] && arr[maxIndex + 1] > arr[i]) {
                        arr[maxIndex] = arr[maxIndex] ^ arr[maxIndex + 1] ^ arr[i];
                        arr[maxIndex + 1] = arr[maxIndex] ^ arr[maxIndex + 1] ^ arr[i];
                        arr[i] = arr[maxIndex] ^ arr[maxIndex + 1] ^ arr[i];
                        arr[maxIndex] = arr[maxIndex] ^ arr[maxIndex + 1] ^ arr[i];
                    } else if (arr[maxIndex + 1] <= arr[maxIndex] && arr[maxIndex] > arr[i]) {
                        arr[i] = arr[i] ^ arr[maxIndex];
                        arr[maxIndex] = arr[i] ^ arr[maxIndex];
                        arr[i] = arr[i] ^ arr[maxIndex];
                    } else if (arr[maxIndex + 1] > arr[maxIndex] && arr[maxIndex + 1] <= arr[i]) {
                        arr[maxIndex + 1] = arr[maxIndex + 1] ^ arr[maxIndex];
                        arr[maxIndex] = arr[maxIndex + 1] ^ arr[maxIndex];
                        arr[maxIndex + 1] = arr[maxIndex + 1] ^ arr[maxIndex];
                    }
                }

            }
            for (int i : arr) {
                System.out.print(i+" ");

            }
            System.out.println();
        }*/
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(executorService);
    }
}
