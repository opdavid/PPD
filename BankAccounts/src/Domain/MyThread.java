package Domain;

import repository.Repository;

public class MyThread implements Runnable {
    private Record record;
    private Repository repo;

    public MyThread(Record record, Repository repo) {
        this.record = record;
        this.repo = repo;
    }

    private boolean verify(){
        return record.getFrom().getMoney() >= record.getSum();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start.");
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand() {
        if(record.getFrom().getId()< record.getTo().getId()){
            try {
                record.getFrom().getMutex().acquire();
                if (verify()){
                    record.getFrom().setMoney(record.getFrom().getMoney() - record.getSum());
                    record.getTo().setMoney(record.getTo().getMoney() + record.getSum());
                    repo.save(record);
                }else{
                    System.out.println("cannot do transaction   "+ record.getFrom().getMoney()+" - "+record.getSum());
                }
                record.getFrom().getMutex().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
                record.getFrom().getMutex().release();
            }
        }
        else {
            try {
                record.getTo().getMutex().acquire();
                if (verify()) {
                    record.getFrom().setMoney(record.getFrom().getMoney() - record.getSum());
                    record.getTo().setMoney(record.getTo().getMoney() + record.getSum());
                    repo.save(record);
                }else{
                    System.out.println("cannot do transaction   " + record.getFrom().getMoney()+" - "+record.getSum());
                }
                record.getTo().getMutex().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
                record.getTo().getMutex().release();
            }
        }

    }

    @Override
    public String toString() {
        return "MyThread{" +
                "record=" + record +
                '}';
    }
}
