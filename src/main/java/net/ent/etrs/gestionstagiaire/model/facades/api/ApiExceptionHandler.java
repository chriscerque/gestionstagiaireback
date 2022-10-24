package net.ent.etrs.gestionstagiaire.model.facades.api;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorVO handleValidationError(MethodArgumentNotValidException ex) {
        System.out.println("ApiExceptionHandler / handleValidationError MethodArgumentNotValidException");
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new ApiErrorVO("VALIDATION_FAILED", defaultMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ApiErrorVO handleValidationError(DataIntegrityViolationException ex) {
        System.out.println("ApiExceptionHandler / handleValidationError DataIntegrityViolationException");
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String defaultMessage = fieldError.getDefaultMessage();
        return new ApiErrorVO("VALIDATION_FAILED", ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiErrorVO handleValidationError(ConstraintViolationException ex) {
        System.out.println("ApiExceptionHandler / handleValidationError ConstraintViolationException");
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String defaultMessage = fieldError.getDefaultMessage();
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiErrorVO("VALIDATION_FAILED", ex.getMessage()));
        return new ApiErrorVO("VALIDATION_FAILED", ex.getMessage());
    }

}
