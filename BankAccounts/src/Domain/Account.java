package Domain;

import java.util.concurrent.Semaphore;

public class Account {
    private Integer id;
    private Integer money;
    private Semaphore mutex;

    public Account() {
    }

    public Account(Integer id, Integer money) {
        this.id = id;
        this.money = money;
        mutex = new Semaphore(1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
