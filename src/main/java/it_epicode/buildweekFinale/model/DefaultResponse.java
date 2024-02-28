package it_epicode.buildweekFinale.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class DefaultResponse {
    private String message;
    private LocalDateTime dataRisposta;
    private Object response;

    public DefaultResponse(String message, Object response){
        this.message = message;
        dataRisposta = LocalDateTime.now();
        this.response = response;
    }
    public DefaultResponse(String message) {
        this.message = message;
        dataRisposta=LocalDateTime.now();
    }

    public static ResponseEntity<DefaultResponse> noObject(String message, HttpStatus status){
        DefaultResponse dr=new DefaultResponse(message);
        return new ResponseEntity<>(dr,status);
    }
    public static ResponseEntity<DefaultResponse> noMessage(Object object, HttpStatus status){
        DefaultResponse dr=new DefaultResponse(status.toString(),object);
        return new ResponseEntity<>(dr,status);
    }
    public static ResponseEntity<DefaultResponse> customMessage(String message,Object obj,HttpStatus status){
        DefaultResponse dr=new DefaultResponse(message,obj);
        return new ResponseEntity<>(dr,status);
    }
}
