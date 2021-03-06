package com.paper.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ResourceBundle;

@Component
public class Validator {

    private static Logger log = Logger.getLogger(Validator.class.getName());

    private static int maxPrintAllowed ;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("printpaper");
        maxPrintAllowed = Integer.parseInt(resourceBundle.getString("max_print_allowed"));
    }

    static String TOTAL_PAGES = "Total Pages";
    static String COLOR_PAGES = "Color Pages";
    static String SIDE = "Printing Side Flag";


    /**
     * @param data
     * @param errorMessages
     *
     * validate input data and some business validation as well
     *
     */
    void validate(String[] data,List<String> errorMessages){
        if(data.length!=3){
            log.error("Invalid job details "+data);
            errorMessages.add("Invalid job details "+data);
            return;
        }

        int totalPages = getValidatedIntFromString(data[0],TOTAL_PAGES,errorMessages);
        int colourPages=  getValidatedIntFromString(data[1],COLOR_PAGES,errorMessages);
        boolean isDoubleSided= getValidatedBooleanFromString(data[2],SIDE,errorMessages);

        if(totalPages<=0){
            log.error("Total pages count should be  +ve="+totalPages);
            errorMessages.add("Invalid total pages count=" + totalPages);
        }

        if(totalPages>maxPrintAllowed){
            log.error("Total pages count should be  less than  "+maxPrintAllowed+" "+totalPages);
            errorMessages.add("Not allowed to print more than "+maxPrintAllowed+" page in a single job");
        }

        if(colourPages<0){
            log.error("Color pages count can't be -ve ="+colourPages);
            errorMessages.add("Color pages count can't be -ve =" + colourPages);
        }
        if(totalPages-colourPages<0){
            log.error("colour pages count can't be more than total pages");
            errorMessages.add("colour pages count can't be more than total pages");
        }

    }


    private int getValidatedIntFromString(String data,String type,List<String> errorMessages){
        int iValue = 0;
        if(data==null || data.trim().length()==0){
            log.error("Invalid input "+data);
            errorMessages.add("Invalid input " +type+"="+ data);
        }

        try {
            iValue = Integer.parseInt(data.trim());
        }catch (NumberFormatException e){
            log.error("Invalid number "+data);
            errorMessages.add("Invalid number " +type+"="+ data);
        }
        return iValue;
    }

    private boolean getValidatedBooleanFromString(String data,String type,List<String> errorMessages){

        if(data==null || data.trim().length()==0 ){
            log.error("Invalid input "+data);
            errorMessages.add("Invalid input " + data);
        }

        if(!(Boolean.TRUE.toString().equals(data.trim().toLowerCase()) ||
                Boolean.FALSE.toString().equals(data.trim().toLowerCase())  )){
            log.error("Invalid boolean flag "+data);
            errorMessages.add("Invalid boolean flag " + data);
        }

        return Boolean.parseBoolean(data.trim());

    }
}
