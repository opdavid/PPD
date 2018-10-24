package service;

import Domain.Account;
import Domain.MyThread;
import Domain.Record;
import repository.AccountRepository;
import repository.RecordRepository;
import repository.Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountManagement {

    private Repository<Record> repository = new RecordRepository();
    private Repository<Account> accountRepository = new AccountRepository();

    public AccountManagement() {
        initAccountRepo();
        for (Account a:accountRepository.getAll())
            System.out.println(a.toString());
    }

    private void initAccountRepo(){
        for(int i = 1; i<=100; i++){
            accountRepository.save(new Account(i,(int)(Math.random()*100)));
        }
    }

    public void app(){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Record record = new Record(accountRepository.get((int)(Math.random()*100)),accountRepository.get((int)(Math.random()*100)),(int)(Math.random()*100));
            Runnable worker = new MyThread(record, repository);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        System.out.println(Record.getSerialNumber());
        for (Record r: repository.getAll()){
            System.out.println(r.toString());
        }
    }
}
