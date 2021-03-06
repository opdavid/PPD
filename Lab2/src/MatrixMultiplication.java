import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {

    public static void run() throws InterruptedException {

        int m, n, c, d, numberThreads1, numberThreads2;
        Random random = new Random();
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns of matrix");
        m = in.nextInt();
        n = m;

        int myMatrixA[][] = new int[m][m];
        int myMatrixB[][] = new int[m][m];
        int myMatrixC[][] = new int[m][m];

        int myMatrixD[][] = new int[m][m];
        int myMatrixE[][] = new int[m][m];

        int myMatrixP[][] = new int [m][m];
        int myMatrixQ[][] = new int[m][m];

        for (c = 0; c < m; c++) {
            for (d = 0; d < n; d++) {
                myMatrixA[c][d] = random.nextInt(10 + 1);
            }
        }

        for (c = 0; c < m; c++) {
            for (d = 0; d < n; d++) {
                myMatrixB[c][d] = random.nextInt(10 + 1);
            }
        }

        for (c = 0; c < m; c++) {
            for (d = 0; d < n; d++) {
                myMatrixC[c][d] = random.nextInt(10 + 1);
            }
        }


        System.out.println("Enter the no of threads 1: ");
        numberThreads1 = in.nextInt();

        System.out.println("Enter the no of threads 2: ");
        numberThreads2 = in.nextInt();

        SharedFiFoQueue sharedQueue = new SharedFiFoQueue(m);

        List<Producer> threads = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(numberThreads1);

        List<Consumer> threads2 = new ArrayList<>();
        ExecutorService executor2 = Executors.newFixedThreadPool(numberThreads2);

        int i = 0;
        while (i < numberThreads1) {
            Producer t = new Producer(myMatrixA, myMatrixB, myMatrixD, i, sharedQueue);
            threads.add(t);
            i++;
        }
        while (i < m) {
            threads.get(i % numberThreads1).addIdx(i);
            i++;
        }

        i = 0;
        while (i < numberThreads2) {
            Consumer t = new Consumer(myMatrixD, myMatrixC, myMatrixE, sharedQueue);
            threads2.add(t);
            i++;
        }

        long startTime = System.currentTimeMillis();

        threads.forEach(executor::execute);
        threads2.forEach(executor2::execute);



        executor.shutdown();
        executor2.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            executor2.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Operation took: " + duration + " milliseconds");

        System.out.println("Multiplying A and B and C equals: ");
        for (i = 0; i<m; i++) {
            for (int j = 0; j < myMatrixB[0].length; j++) {
                for (int k = 0; k < myMatrixA[0].length; k++) {
                    myMatrixP[i][j] += myMatrixA[i][k] * myMatrixB[k][j];
                }
            }
        }

        for (i = 0; i<m; i++) {
            for (int j = 0; j < myMatrixP[0].length; j++) {
                for (int k = 0; k < myMatrixC[0].length; k++) {
                    myMatrixQ[i][j] += myMatrixP[i][k] * myMatrixC[k][j];
                }
            }
        }

        for (c = 0; c < m; c++) {
            for (d = 0; d < n; d++) {
                System.out.print(myMatrixQ[c][d] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        for(c = 0; c < m; c++) {
            for( d = 0; d < m; d++) {
                System.out.print(myMatrixE[c][d] + " ");
            }
            System.out.println();
        }
    }
}
