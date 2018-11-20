public class Consumer extends Thread {

    private int[][] first;
    private int[][] second, sum;

    private final SharedFiFoQueue queue;


    public Consumer(int[][] first, int[][] second, int[][] sum, SharedFiFoQueue queue) {
        this.first = first;
        this.sum = sum;
        this.second = second;
        this.queue = queue;
    }

    @Override
    public void run() {
        Object o;
        int i;
        try {
            while ( (o = queue.remove()) != null){
                System.out.println("consumer: " + o);
                i = (int) o;

                for (int j = 0; j < second[0].length; j++) {
                    //now complete the addition and multiplication
                    for (int k = 0; k < first[0].length; k++) {
                        sum[i][j] += first[i][k] * second[k][j];
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("An InterruptedException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
