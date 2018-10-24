package repository;

import Domain.Account;
import Domain.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository implements Repository<Record> {

    private List<Record> records;

    public RecordRepository() {
        this.records = new ArrayList<>();
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public Integer save(Record entity) {
        records.add(entity);
        return Record.getSerialNumber();
    }

    @Override
    public Record update(Integer id, Record entity) {
        return null;
    }

    @Override
    public Record delete(Integer id) {
        return null;
    }

    @Override
    public List<Record> getAll() {
        return records;
    }

    @Override
    public Record get(Integer id) {
        for(Record r: records){
            if (r.getSerialNumber().equals(id))
                return r;
        }
        return new Record();
    }
}
