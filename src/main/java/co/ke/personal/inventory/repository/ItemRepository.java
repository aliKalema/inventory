package co.ke.personal.inventory.repository;

import co.ke.personal.inventory.model.Item;
import co.ke.personal.inventory.model.MoveType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
    Optional<Item> findByRefId(String refId);
    @Query(value = "SELECT * FROM item t WHERE cast(created as date) = :localDate",nativeQuery = true)
    List<Item> findByCreated(LocalDate localDate);

    @Query(value="SELECT * FROM  item t LEFT JOIN inventory_movement im ON t.ref_id = im.item_ref_id AND cast(im.moved_date_time as date) = :localDate AND im.move_type = :moveType", nativeQuery = true)
    public List<Item> findByMovedDateAndMoveType(LocalDate localDate, MoveType moveType);

    @Query(value="SELECT * FROM  item t LEFT JOIN inventory_movement im ON t.ref_id = im.item_ref_id AND im.moved_date_time >= :startDate AND im.moved_date_time as date =< :endDate AND im.move_type = :moveType", nativeQuery = true)
    public List<Item> findByMovedDateRangeAndMoveType(LocalDate startDate, LocalDate endDate, MoveType moveType);

}
