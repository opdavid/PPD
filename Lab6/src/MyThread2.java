import java.math.BigInteger;
import java.util.concurrent.Semaphore;

public class MyThread2 implements Runnable {

    private BigInteger[] polinomial;
    private int power;
    private BigInteger value;
    private BigInteger[] result;
    private Semaphore mutex;

    public MyThread2(BigInteger[] polinomial, int power,BigInteger value, BigInteger[] result) {
        this.polinomial = polinomial;
        this.power = power;
        this.value = value;
        this.result = result;
        mutex = new Semaphore(1);
    }

    @Override
    public void run() {
        for (int j = 0; j < this.polinomial.length; j++) {
            try {
                mutex.acquire();
                result[j+this.power] = result[j+this.power].add(Karatsuba.karatsuba(this.polinomial[j], this.value));
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
