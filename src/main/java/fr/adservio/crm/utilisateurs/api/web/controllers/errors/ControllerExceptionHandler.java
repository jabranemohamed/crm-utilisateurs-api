package fr.adservio.crm.utilisateurs.api.web.controllers.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static fr.adservio.crm.utilisateurs.api.web.controllers.errors.LocationType.requestParameters;
import static fr.adservio.crm.utilisateurs.api.web.controllers.errors.LocationType.requestBody;
import static fr.adservio.crm.utilisateurs.api.web.controllers.errors.LocationType.requestHeader;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String SERVER_ERROR = "Server Error";
    public static final String RESOURCE_NOT_FOUND = "Resource Not Found";
    public static final String RESQUEST_BODY = "requestBody";
    public static final String RESQUEST_HEADER = "requestHeader";
    public static final String VALIDATION_FAILED = "Validation Failed";
    public static final String reasonCode = "validationError";

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();
        errorMessageDescription = errorMessageDescription == null ? ex.toString() : errorMessageDescription;
        ErrorResponse error = new ErrorResponse(errorMessageDescription, RESQUEST_BODY, requestBody,reasonCode);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ResourceNotFound.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFound ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();
        errorMessageDescription = errorMessageDescription == null ? ex.toString() : errorMessageDescription;
        ErrorResponse error = new ErrorResponse(errorMessageDescription, RESQUEST_HEADER, requestBody,reasonCode);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse(RESOURCE_NOT_FOUND, RESQUEST_HEADER, requestHeader,reasonCode);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
