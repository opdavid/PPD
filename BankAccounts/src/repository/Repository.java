package repository;

import java.util.List;

public interface Repository<T> {

    Integer save(T entity);
    T update(Integer id,T entity);
    T delete(Integer id);
    T get(Integer id);
    List<T> getAll();
}
