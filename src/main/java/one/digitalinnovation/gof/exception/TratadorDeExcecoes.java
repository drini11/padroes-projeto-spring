package one.digitalinnovation.gof.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> tratarErrosDeValidacao(MethodArgumentNotValidException ex){
        List<ErroValidacaoDTO> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErroValidacaoDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

}
