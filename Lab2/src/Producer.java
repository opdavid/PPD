import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {

    private int[][] first, second, sum;
    private List<Integer> idx = new ArrayList<>();

    private final SharedFiFoQueue queue;

    public Producer(int[][] first, int[][] second, int[][] sum, int i, SharedFiFoQueue queue) {
        this.first = first;
        this.second = second;
        this.sum = sum;
        this.queue = queue;
        addIdx(i);
    }

    public void addIdx(int i) {
        this.idx.add(i);
    }

    @Override
    public void run() {
        /**
         * For multiplication
         */
        try {
            for (Integer i : idx) {
                for (int j = 0; j < second[0].length; j++) {
                    //now complete the addition and multiplication
                    for (int k = 0; k < first[0].length; k++) {
                        sum[i][j] += first[i][k] * second[k][j];
                    }
                    queue.add(sum[i][j]);
                }
            }
            queue.add(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
