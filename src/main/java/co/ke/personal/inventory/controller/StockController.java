package co.ke.personal.inventory.controller;

import co.ke.personal.inventory.model.MoveType;
import co.ke.personal.inventory.payload.StockRequest;
import co.ke.personal.inventory.service.InventoryMovementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stock")
public record StockController(InventoryMovementService inventoryMovementService) {

    @PostMapping
    public void receiveFromVendor(@RequestBody @Valid StockRequest stockRequest){
        if(stockRequest.getMoveType().equals("RECEIVED_FROM_VENDOR"))
            inventoryMovementService.receiveStockFromVendor(stockRequest);
        else if(stockRequest.getMoveType().equals("RELEASE_TO_STORE"))
            inventoryMovementService.moveInventoryToSupermarket(stockRequest);
        else if (stockRequest.getMoveType().equals("RETURN_TO_VENDOR"))
            inventoryMovementService.returnSpoilInventory(stockRequest);

    }

}
