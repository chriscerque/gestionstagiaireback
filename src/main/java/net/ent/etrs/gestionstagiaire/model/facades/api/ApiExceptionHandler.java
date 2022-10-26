package net.ent.etrs.gestionstagiaire.model.facades.api;

import lombok.extern.apachecommons.CommonsLog;
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
@CommonsLog(topic = "SOUT")
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorVO handleValidationError(MethodArgumentNotValidException ex) {
        log.trace("ApiExceptionHandler / handleValidationError MethodArgumentNotValidException");
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new ApiErrorVO("VALIDATION_FAILED", defaultMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(code = HttpStatus.CONFLICT)
//    @ResponseBody
    public ResponseEntity<ApiErrorVO> handleValidationError(DataIntegrityViolationException ex) {
        log.trace("ApiExceptionHandler / handleValidationError DataIntegrityViolationException");
//        ex.printStackTrace();
        ApiErrorVO apiErrorVO;
        Throwable th = ex.getCause();
        log.trace("ex.getCause().getClass() : " + ex.getCause().getClass());
        if (th instanceof org.hibernate.exception.ConstraintViolationException) {
//            log.trace("((ConstraintViolationException) ex.getCause()).getConstraintName() : " + ((ConstraintViolationException) ex.getCause()).getConstraintName());
            log.trace("th.getCause().getClass() : " + th.getCause().getClass());
            apiErrorVO = new ApiErrorVO("VALIDATION_FAILED ConstraintViolationException", th.getCause().getMessage());
            log.trace("apiErrorVO : " + apiErrorVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorVO);
        } else {
            apiErrorVO = new ApiErrorVO("VALIDATION_FAILED ConstraintViolationException", th.getMessage());
            log.trace("apiErrorVO : " + apiErrorVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorVO);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiErrorVO handleValidationError(ConstraintViolationException ex) {
        log.trace("ApiExceptionHandler / handleValidationError ConstraintViolationException");
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
        log.trace("ApiExceptionHandler / handleValidationError SQLException");
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String defaultMessage = fieldError.getDefaultMessage();
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiErrorVO("VALIDATION_FAILED", ex.getMessage()));
        ApiErrorVO apiErrorVO = new ApiErrorVO("VALIDATION_FAILED", ex.getMessage());
        log.trace("apiErrorVO : " + apiErrorVO);
        return apiErrorVO;
    }

}
