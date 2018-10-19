package com.bets.mgr.exception;

import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.jws.WebResult;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

// Interceptor. A todos los controllers de la aplicación, agrégale esta parte siguiente también
// El controlador gestionará la excepción de tipo NotFound y la reenviará a este método, para que sea el que la trate realm,ente
// Hacerlo para todos los controladores de una sola vez
@ControllerAdvice
public class BetsAppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messages;

    @Override
    protected ResponseEntity <Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Locale locale = request.getLocale();

        BindingResult result = ex.getBindingResult();

        Collection collect = result.getAllErrors().stream()
                                        //.map(e -> e.getDefaultMessage() )
                                        .map(e -> messages.getMessage(e, locale))
                                        .collect( Collectors.toList() );

        BetsAppExceptionResponse exceptionResponse =
                new BetsAppExceptionResponse( LocalDateTime.now(), "Bad request", collect.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request) {

        BetsAppExceptionResponse exceptionResponse =
                new BetsAppExceptionResponse( LocalDateTime.now(), e.getMessage(), request.getDescription( false ) );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {

        BetsAppExceptionResponse exceptionResponse =
                new BetsAppExceptionResponse( LocalDateTime.now(), e.getMessage(), request.getDescription( false ) );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


// Formato genérico para tratar las excepciones
// Encapsular los errores para dar sentido desde el punto de vista de negocio
// Unificar mensajes de error que se devuelven al desarrollador front

