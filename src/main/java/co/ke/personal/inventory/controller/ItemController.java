package co.ke.personal.inventory.controller;

import co.ke.personal.inventory.model.Item;
import co.ke.personal.inventory.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/items")

public record ItemController(ItemService itemService) {
    @GetMapping
    public ResponseEntity<?> getItems(){
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

    @GetMapping("/{refId}")
    public ResponseEntity<Item> getItem(@PathVariable("refId") String refId){
        return new ResponseEntity<>(itemService.getItemByRefId(refId), HttpStatus.OK);
    }

    @GetMapping("/created/today")
    public ResponseEntity<List<Item>> getItemsCreatedToday(){
        return new ResponseEntity<>(itemService.getByCreated(LocalDate.now()), HttpStatus.OK);
    }

    @GetMapping("/received/lastWeek")
    public ResponseEntity<List<Item>> getItemsAddedLastWeek(){
        return new ResponseEntity<>(itemService.getReceivedFromVendorLastWeek(), HttpStatus.OK);
    }

    @GetMapping("/released/today")
    public ResponseEntity<List<Item>> getReleaseToSupermarketToday(){
        return new ResponseEntity<>(itemService.getReleasedToSupermarketToday(), HttpStatus.OK);
    }

    @GetMapping("/returned/lastMonth")
    public ResponseEntity<List<Item>> returnedToVendorLastMonth(){
        return new ResponseEntity<>(itemService.returnedToVendorLastMonth(), HttpStatus.OK);
    }

    @PutMapping("/{refId}")
    public ResponseEntity<Item> updateItem(@PathVariable("refId") String refId, @RequestBody Item item){
        return new ResponseEntity<>(itemService.updateItem(refId, item), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody Item item){
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/{refId}")
    public ResponseEntity<?> delete(@PathVariable String refId) {
        itemService.deleteItem(refId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
