package com.paper.exception;


import java.util.List;

public class CustomException  extends  RuntimeException{

    static  String lineSepartor = "\n";
    private String message;

    public CustomException(String message) {
        super(message);
        message = message;
    }

}
