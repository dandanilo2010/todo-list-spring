package com.example.demo.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String mensagem){
        super(mensagem);
    }

}