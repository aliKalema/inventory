package co.ke.personal.inventory.exception;

public class QuantityOutOfBound extends RuntimeException{
    public QuantityOutOfBound(String message){
        super(message);
    }
}
