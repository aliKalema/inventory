package co.ke.personal.inventory.repository;

import co.ke.personal.inventory.model.InventoryMovement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InventoryMovementRepository extends CrudRepository<InventoryMovement, Long> {
    @Query(value = "SELECT SUM(quantity) FROM inventory_movement WHERE item_ref_id = ?", nativeQuery = true)
    public Integer findQuantityInStore(String itemRefId);

}
