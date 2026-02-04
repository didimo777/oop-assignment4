package assignment4.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    ID save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}
