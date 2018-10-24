package repository;

import Domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {

    private List<Account> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts() {
        this.accounts = new ArrayList<>();
    }

    @Override
    public Integer save(Account entity) {
        accounts.add(entity);
        return entity.getId();
    }

    @Override
    public Account update(Integer id, Account entity) {
        return null;
    }

    @Override
    public Account delete(Integer id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public Account get(Integer id) {
        for(Account a: accounts){
            if (a.getId().equals(id))
                return a;
        }
        return new Account();
    }
}
