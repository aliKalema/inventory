package co.ke.personal.inventory.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public enum MoveType {
    RECEIVED_FROM_VENDOR,
    RELEASE_TO_STORE,
    RETURN_TO_VENDOR;

    public static MoveType fromText(String text) {
       if(text.equals("RECEIVED_FROM_VENDOR"))
           return MoveType.RECEIVED_FROM_VENDOR;
        else if(text.equals("RELEASE_TO_STORE"))
            return MoveType.RELEASE_TO_STORE;
        else if(text.equals("RETURN_TO_VENDOR"))
            return MoveType.RETURN_TO_VENDOR;
        else
            return null;
    }
}

