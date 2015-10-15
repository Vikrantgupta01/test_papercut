package com.paper.exception;


import java.util.List;

public class CustomException  extends  RuntimeException{

    private List<String> errorMessages;
    private String message;
    private static String LINE_SEPARATOR="\n";

    public CustomException(List<String> messages) {
        errorMessages = messages;
    }
    public CustomException(String message) {
        super(message);
        this.message = message;
    }


    public String getMessage() {
        StringBuffer bufferMsg = new StringBuffer();
        if(errorMessages ==null){
            return message;
        }
        for (String message : errorMessages){
            bufferMsg.append(message);
            bufferMsg.append(LINE_SEPARATOR);
        }
        return bufferMsg.toString();
    }

}
