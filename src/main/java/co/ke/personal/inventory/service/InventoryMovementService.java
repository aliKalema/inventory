package co.ke.personal.inventory.service;

import co.ke.personal.inventory.model.Vendor;
import co.ke.personal.inventory.payload.StockRequest;
import co.ke.personal.inventory.repository.StockRepository;
import co.ke.personal.inventory.repository.StoreRepository;
import co.ke.personal.inventory.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMovement {
    private final ItemService itemService;
    private final VendorRepository vendorRepository;
    private final StockRepository stockRepository;
    private final StoreRepository storeRepository;

    public void moveInventoryToStore(StockRequest stockRequest){

    }

    public void returnSpoilInventory(StockRequest stockRequest){

    }

}
