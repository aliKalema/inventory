package co.ke.personal.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class QuantityOutOfBoundException extends RuntimeException{
    public QuantityOutOfBoundException(){
        super("Quantity to be returned to Vendor can not be greater than Quantities in the Inventory");
    }
}
