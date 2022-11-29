package co.ke.personal.inventory.service;

import co.ke.personal.inventory.exception.ItemNotFoundException;
import co.ke.personal.inventory.model.Item;
import co.ke.personal.inventory.model.MoveType;
import co.ke.personal.inventory.repository.ItemRepository;
import co.ke.personal.inventory.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
   private final ItemRepository itemRepository;
   public List<Item> getItems() {
      return StreamSupport.stream(itemRepository.findAll().spliterator(), false).collect(Collectors.toList());
   }
   public Item getItemByRefId(String refId) {
      return itemRepository.findByRefId(refId).orElseThrow(()->new ItemNotFoundException(String.format("Item of RefId: %s Not Found", refId)));
   }
   public  void deleteItem(String refId) {
      Item item =  this.getItemByRefId(refId);
      itemRepository.delete(item);
   }
   public Item createItem(Item item) {
      if(item.getCreated() == null) {
         item.setCreated(LocalDateTime.now());
      }
      item.setRefId(StringUtils.generateRefId());
      return itemRepository.save(item);
   }
   public Item updateItem(String refId, Item item) {
      Item original =  this.getItemByRefId(refId);
      Item updated =  original.update(item);
      return itemRepository.save(updated);
   }
   public List<Item> getByCreated(LocalDate localDate){
      return itemRepository.findByCreated(localDate);
   }

   public List<Item> getReleasedToSupermarketToday(){
      return itemRepository.findByMovedDateAndMoveType(LocalDate.now(), MoveType.RELEASE_TO_STORE.name());
   }

   public List<Item> returnedToVendorLastMonth(){
      LocalDate startOfMonth = getPreviousMonth(LocalDate.now()).get("START_OF_MONTH");
      LocalDate endOfMOnth = getPreviousMonth(LocalDate.now()).get("END_OF_MONTH");
      return itemRepository.findByMovedDateRangeAndMoveType(startOfMonth, endOfMOnth, MoveType.RETURN_TO_VENDOR.name());
   }

   public List<Item> getReceivedFromVendorLastWeek() {
      LocalDate sunday = getPreviousWeek(LocalDate.now()).get("SUNDAY");
      LocalDate saturday =  getPreviousWeek(LocalDate.now()).get("SATURDAY");
      return itemRepository.findByMovedDateRangeAndMoveType(sunday, saturday, MoveType.RECEIVED_FROM_VENDOR.name());
   }

   public Map<String, LocalDate> getPreviousWeek(LocalDate localDate){
      LocalDate previousEndOfWeek = localDate.with( TemporalAdjusters.previous( DayOfWeek.SATURDAY ) ) ;
      LocalDate previousStartOfWeek =  previousEndOfWeek.minusDays(6);
      Map<String,LocalDate> map =  new HashMap<>();
      map.put("SUNDAY", previousStartOfWeek);
      map.put("SATURDAY", previousEndOfWeek);
      return map;
   }
   public Map<String, LocalDate>  getPreviousMonth(LocalDate localDate){
      Map<String, LocalDate>map =  new HashMap<>();
      LocalDate endOfMonth = YearMonth.from(localDate).minusMonths(1).atEndOfMonth();
      LocalDate startOfMonth = endOfMonth.with(TemporalAdjusters.firstDayOfMonth());
      map.put("START_OF_MONTH", startOfMonth);
      map.put("END_OF_MONTH", endOfMonth);
      return map;
   }

}
