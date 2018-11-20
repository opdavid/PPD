import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;



class AddTwoMatrix {
    public static void run() {
        int m, n, c, d, numberThreads, i;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns of matrix");
        m = in.nextInt();
        n = in.nextInt();

        int first[][] = new int[m][n];
        int second[][] = new int[m][n];
        int sum[][] = new int[m][n];

        System.out.println("Enter the elements of first matrix");

        for (c = 0; c < m; c++)
            for (d = 0; d < n; d++)
                first[c][d] = in.nextInt();

        System.out.println("Enter the elements of second matrix");

        for (c = 0; c < m; c++)
            for (d = 0; d < n; d++)
                second[c][d] = in.nextInt();

        System.out.println("Enter the no of threads");
        numberThreads = in.nextInt();

        List<MyThread> threads = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(numberThreads);


        i = 0;
        while(i<numberThreads) {
            MyThread t = new MyThread(first, second, sum, i);
            threads.add(t);
            i++;
        }
        while(i<m){
            System.out.println(i);
            threads.get(i%numberThreads).addIdx(i);
            i++;
        }

        long startTime = System.currentTimeMillis();

        threads.forEach(executor::execute);

//        threads.forEach(MyThread::run);

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);

        System.out.println("Operation took: " + duration + " milliseconds");
//        for (c = 0; c < m; c++)
//            for (d = 0; d < n; d++)
//                sum[c][d] = first[c][d] + second[c][d];  //replace '+' with '-' to subtract matrices

        System.out.println("Sum of the matrices:");

        for (c = 0; c < m; c++) {
            for (d = 0; d < n; d++)
                System.out.print(sum[c][d] + "\t");

            System.out.println();
        }
    }
}