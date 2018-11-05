import java.util.ArrayList;
import java.util.List;

public class MyThread implements Runnable {

    private int[][] first,second,sum;
    private List<Integer> idx = new ArrayList<>();

    public MyThread(int[][] first, int[][] second, int[][] sum, int i) {
        this.first = first;
        this.second = second;
        this.sum = sum;
        addIdx(i);
    }

    public void addIdx(int i){
        this.idx.add(i);
    }

    @Override
    public void run() {
        for(Integer i:idx) {
//            for (int j = 0; j < first[0].length; j++) {
//                sum[i][j] = first[i][j] + second[i][j];
//            }
            for (int j = 0; j < second[0].length; j++) {
                //now complete the addition and multiplication
                for (int k = 0; k < first[0].length; k++) {
                    sum[i][j] += first[i][k] * second[k][j];
                }
            }
        }
    }
}
