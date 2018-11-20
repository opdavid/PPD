import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {
    private Queue<Object> elems;
    private int counter = 0;
    private final int capacity;

    private final Lock lock = new ReentrantLock();

    private final Condition isEmpty = lock.newCondition();

    private final Condition isFull = lock.newCondition();



    public SharedFiFoQueue(int capacity) {
        this.elems = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void add(Object elem) throws InterruptedException {

        lock.lock();
        elems.add(elem);
        System.out.println("add: " +counter);
        notifyAll();
        //Notify the consumer that there is data available.
        isEmpty.signal();
        ++counter;
        lock.unlock();
    }

    public synchronized Object remove() throws InterruptedException {
        Object elem;
        lock.lock();

        while (this.elems.isEmpty())
            isEmpty.await();

        System.out.println("remove: "+ counter);

        if(counter == this.capacity) {
            System.out.println("asssa");
            lock.unlock();
            return null;
        }

        elem = this.elems.poll();

//        isFull.signal();
        notifyAll();

        lock.unlock();

        return elem;
    }
}
