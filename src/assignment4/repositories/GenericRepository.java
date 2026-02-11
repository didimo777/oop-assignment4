package assignment4.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);

    default boolean existsById(ID id) {
        return findById(id).isPresent();
    }
    static boolean isValidId(int id) {
        return id > 0;
    }
}