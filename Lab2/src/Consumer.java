import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Consumer extends Thread {

    private int[][] first, sum;
    private List<Integer> idx = new ArrayList<>();

    private int total = 0;
    private final SharedFiFoQueue queue;


    public Consumer(int[][] first, int[][] sum, int i, SharedFiFoQueue queue) {
        this.first = first;
        this.sum = sum;
        this.queue = queue;
        addIdx(i);
    }

    public void addIdx(int i) {
        this.idx.add(i);
    }

    @Override
    public void run() {
        try {
            do {
                Object obj = queue.remove();

                if (obj == null)
                    break;

                System.out.println("[Consumer] Read the element: " + obj.toString());
            } while (true);

        } catch (InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("\n[Consumer] " + total + " distinct words have been read...");
    }

}
