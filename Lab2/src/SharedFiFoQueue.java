import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {
    private Queue<Object> elems;

    private volatile boolean available = false;
    private int counter = 0;
    private int removeidx = 0;
    private final int capacity;

    private final Lock lock = new ReentrantLock();

    private final Condition isEmpty = lock.newCondition();

    private final Condition isFull = lock.newCondition();


    public SharedFiFoQueue(int capacity) {
        this.elems = new LinkedList<>();
        this.capacity = capacity;
    }

    public void add(Object elem) throws InterruptedException {

        lock.lock();
        while (available) {
            isFull.await();
        }

        elems.add(elem);
//        available = true;

        //Notify the consumer that there is data available.
        isEmpty.signal();
        ++counter;
        lock.unlock();
    }

    public Object remove() throws InterruptedException {
        Object elem;
        lock.lock();

        while (this.elems.isEmpty() && removeidx != this.capacity) {
            isEmpty.await();
        }

        if (removeidx == this.capacity && removeidx == counter) {
            lock.unlock();
            return null;
        }

        elem = this.elems.poll();
        removeidx++;
        available = false;

        isFull.signal();

        lock.unlock();

        return elem;
    }
}
