import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Karatsuba {
    private final static BigInteger ZERO = new BigInteger("0");

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {

        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);                // optimize this parameter

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac    = karatsuba(a, c);
        BigInteger bd    = karatsuba(b, d);
        BigInteger abcd  = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));
    }


    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        System.out.println("Give the order of first polin:");
        n = scanner.nextInt();
        n++;
        System.out.println("Give the order of second polin:");
        m = scanner.nextInt();
        m++;
        BigInteger[] polin1 = new BigInteger[n];
        BigInteger[] polin2 = new BigInteger[m];
        BigInteger[] res = new BigInteger[n + m - 1];

        ExecutorService executorService = Executors.newFixedThreadPool(n);

        BigInteger[] res2 = new BigInteger[n+m];
        for (int i = 0; i < res.length; i++) {
            res[i] = BigInteger.valueOf(0);
            res2[i] = BigInteger.valueOf(0);
        }

        for (int i = 0; i < m; i++) {
            polin2[i] = BigInteger.valueOf((int) (Math.random() * 100));
        }

        for (int i = 0; i < n; i++) {
            polin1[i] = BigInteger.valueOf((int) (Math.random() * 100));
            MyThread2 t = new MyThread2(polin2, i, polin1[i], res);
            executorService.execute(t);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                res2[j+k] = res2[j+k].add(Karatsuba.karatsuba(polin1[j], polin2[k]));

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