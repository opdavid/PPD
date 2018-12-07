import java.util.concurrent.Semaphore;

public class MyThread implements Runnable {

    private int[] polinomial;
    private int power;
    private int value;
    private int[] result;
    private Semaphore mutex;

    public MyThread(int[] polinomial, int power,int value, int[] result) {
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
                result[j+this.power] = result[j+this.power] + this.polinomial[j] * this.value;
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
