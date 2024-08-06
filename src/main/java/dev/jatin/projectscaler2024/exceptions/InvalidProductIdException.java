package dev.jatin.projectscaler2024.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidProductIdException extends Exception{


private Long productId;
    public InvalidProductIdException(Long productId,String message){
        super(message);
        this.productId = productId;

    }
}
