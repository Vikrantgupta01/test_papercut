package com.paper.service;

import com.paper.exception.CustomException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    static Logger log = Logger.getLogger(Validator.class.getName());

    void validate(String[] data){
        int totalPages = getValidatedIntFromString(data[0]);
        int colourPages=  getValidatedIntFromString(data[1]);
        boolean isDoubleSided= getValidatedBooleanFromString(data[2]);

        if(colourPages<0){
            log.error("Color pages count can't be -ve ="+colourPages);
            throw  new CustomException("Color pages count can't be -ve ="+colourPages);
        }
        if(totalPages-colourPages<0){
            log.error("B&W pages count can't be -ve ="+colourPages);
            throw  new CustomException("B&W  pages count can't be -ve ="+colourPages);
        }

    }


    private int getValidatedIntFromString(String data){
        int i = 0;
        if(data==null || data.trim().length()==0){
            log.error("Invalid input "+data);
            throw  new CustomException("Invalid input "+data);
        }

        try {
            i = Integer.parseInt(data.trim());
        }catch (NumberFormatException e){
            log.error("Invalid number "+data);
            throw  new CustomException("Invalid number "+data);
        }
        return i;

    }

    private boolean getValidatedBooleanFromString(String data){
        boolean b = false;
        if(data==null || data.trim().length()==0 ){
            log.error("Invalid input "+data);
            throw  new CustomException("Invalid input "+data);
        }

        if(!(Boolean.TRUE.toString().equals(data.trim().toLowerCase()) ||
                Boolean.FALSE.toString().equals(data.trim().toLowerCase())  )){
            log.error("Invalid boolean flag "+data);
            throw  new CustomException("Invalid boolean flag "+data);
        }

        return Boolean.parseBoolean(data.trim());

    }
}
