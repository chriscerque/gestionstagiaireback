package net.ent.etrs.gestionstagiaire.model.facades.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

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
//    @ResponseStatus(code = HttpStatus.CONFLICT)
//    @ResponseBody
    public ResponseEntity<ApiErrorVO> handleValidationError(DataIntegrityViolationException ex) {
        System.out.println("ApiExceptionHandler / handleValidationError DataIntegrityViolationException");
//        ex.printStackTrace();
        ApiErrorVO apiErrorVO;
        Throwable th = ex.getCause();
        System.out.println("ex.getCause().getClass() : " + ex.getCause().getClass());
        if (th instanceof org.hibernate.exception.ConstraintViolationException) {
//            System.out.println("((ConstraintViolationException) ex.getCause()).getConstraintName() : " + ((ConstraintViolationException) ex.getCause()).getConstraintName());
            System.out.println("th.getCause().getClass() : " + th.getCause().getClass());
            apiErrorVO = new ApiErrorVO("VALIDATION_FAILED ConstraintViolationException", th.getCause().getMessage());
            System.out.println("apiErrorVO : " + apiErrorVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorVO);
        } else {
            apiErrorVO = new ApiErrorVO("VALIDATION_FAILED ConstraintViolationException", th.getMessage());
            System.out.println("apiErrorVO : " + apiErrorVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorVO);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiErrorVO handleValidationError(ConstraintViolationException ex) {
        System.out.println("ApiExceptionHandler / handleValidationError ConstraintViolationException");
//        ex.printStackTrace();
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String defaultMessage = fieldError.getDefaultMessage();
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiErrorVO("VALIDATION_FAILED", ex.getMessage()));
        return new ApiErrorVO("VALIDATION_FAILED", ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiErrorVO handleValidationError(SQLException ex) {
        System.out.println("ApiExceptionHandler / handleValidationError SQLException");
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String defaultMessage = fieldError.getDefaultMessage();
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiErrorVO("VALIDATION_FAILED", ex.getMessage()));
        ApiErrorVO apiErrorVO = new ApiErrorVO("VALIDATION_FAILED", ex.getMessage());
        System.out.println("apiErrorVO : " + apiErrorVO);
        return apiErrorVO;
    }

}
