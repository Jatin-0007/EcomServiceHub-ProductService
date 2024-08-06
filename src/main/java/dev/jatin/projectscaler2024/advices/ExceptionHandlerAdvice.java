package dev.jatin.projectscaler2024.advices;

import dev.jatin.projectscaler2024.Dto.ArrayIndexOutOfBoundDto;
import dev.jatin.projectscaler2024.Dto.ArthematicExceptionDto;
import dev.jatin.projectscaler2024.Dto.exceptionDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArthematicExceptionDto> ArthematicException(){
        ArthematicExceptionDto dto = new ArthematicExceptionDto();
        dto.setMessage("Someting went Wrong");
        dto.setDetails("Problem Error");

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundDto> handleAIOBException(){
        ArrayIndexOutOfBoundDto dto = new ArrayIndexOutOfBoundDto();
        dto.setMessage("Someting went Wrong");
        dto.setDetails("Problem Error");

        return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<exceptionDto> handleInvalidProductIdException(InvalidProductIdException exception){
        exceptionDto dto = new exceptionDto();

        dto.setProductId(exception.getProductId());


        dto.setMessage("Id is Invalid,retry with new Id ");
        dto.setDetails("The entered Id is incorrect");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
