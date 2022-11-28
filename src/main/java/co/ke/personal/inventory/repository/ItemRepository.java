package co.ke.personal.inventory.repository;

import co.ke.personal.inventory.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
    void deleteByRefId(String refId);

    Optional<Item> findByRefId(String refId);
}
