import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        simpleRun();
        Karatsuba.run();
    }

    public static void simpleRun() {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        System.out.println("Give the order of first polin:");
        n = scanner.nextInt();
        n++;
        System.out.println("Give the order of second polin:");
        m = scanner.nextInt();
        m++;
        int[] polin1 = new int[n];
        int[] polin2 = new int[m];
        int[] res = new int[n + m - 1];

        ExecutorService executorService = Executors.newFixedThreadPool(n);

        int[] res2 = new int[n+m];
        for (int i = 0; i < res.length; i++) {
            res[i] = 0;
            res2[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            polin2[i] = (int) (Math.random() * 100);
        }

        for (int i = 0; i < n; i++) {
            polin1[i] = (int) (Math.random() * 100);
            MyThread t = new MyThread(polin2, i, polin1[i], res);
            executorService.execute(t);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                res2[j+k] = res2[j+k] + polin1[j] * polin2[k];

            }
        }
        for (int i = 0; i < polin1.length; i++) {
            System.out.print(polin1[i] + " ");
        }

        System.out.println();
        for (int i = 0; i < polin2.length; i++) {
            System.out.print(polin2[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < res.length; i++) {
            System.out.print(res2[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

        System.out.println();
        System.out.println("Hello world");
    }
}
