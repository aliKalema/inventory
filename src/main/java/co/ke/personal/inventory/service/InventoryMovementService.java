package co.ke.personal.inventory.service;

import co.ke.personal.inventory.exception.QuantityOutOfBoundException;
import co.ke.personal.inventory.model.InventoryMovement;
import co.ke.personal.inventory.model.Item;
import co.ke.personal.inventory.model.MoveType;
import co.ke.personal.inventory.payload.StockRequest;
import co.ke.personal.inventory.repository.InventoryMovementRepository;
import co.ke.personal.inventory.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMovementService {
    private final ItemService itemService;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final ItemRepository itemRepository;

    public void receiveStockFromVendor(StockRequest stockRequest){
        Item item = itemService.getItemByRefId(stockRequest.getItemRefId());
        item.setRunningQuantity(item.getRunningQuantity() + stockRequest.getQuantity());
        itemRepository.save(item);
        InventoryMovement inventoryMovement = new InventoryMovement(item, stockRequest.getQuantity());
        inventoryMovement.setMoveType(MoveType.RECEIVED_FROM_VENDOR);
        inventoryMovementRepository.save(inventoryMovement);
    }
    public void moveInventoryToSupermarket(StockRequest stockRequest){
        Item item = validate(stockRequest);
        item.setRunningQuantity(item.getRunningQuantity() - stockRequest.getQuantity());
        itemRepository.save(item);
        InventoryMovement inventoryMovement = new InventoryMovement(item, stockRequest.getQuantity());
        inventoryMovement.setMoveType(MoveType.RELEASE_TO_STORE);
        inventoryMovementRepository.save(inventoryMovement);
    }
    public void returnSpoilInventory(StockRequest stockRequest){
        Item item = validate(stockRequest);
        //subtract the quantity returned to the vendor from the inventory
        item.setRunningQuantity(item.getRunningQuantity() - stockRequest.getQuantity());
        itemRepository.save(item);
        //make the movement
        InventoryMovement inventoryMovement = new InventoryMovement(item, stockRequest.getQuantity());
        inventoryMovement.setMoveType(MoveType.RETURN_TO_VENDOR);
        inventoryMovementRepository.save(inventoryMovement);
    }
    private Item validate(StockRequest stockRequest){
        Item item = itemService.getItemByRefId(stockRequest.getItemRefId());
        Integer totalQuantities = inventoryMovementRepository.findQuantityInStore(stockRequest.getItemRefId()) + item.getRunningQuantity();
        //validate that the spoilt quantity to be return is not more than items in the store and inventory
        if(stockRequest.getQuantity()> totalQuantities){
            throw new QuantityOutOfBoundException();
        }
        return item;
    }


}
