package com.paper.service;

import com.paper.JobProcessor;
import com.paper.model.PaperType;
import com.paper.model.PrintinType;
import com.paper.model.PrintingColor;
import com.paper.model.PrintingJob;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component("jobProcessor")
public class JobProcessorImpl implements JobProcessor{

    private  static ResourceBundle resourceBundle = ResourceBundle.getBundle("printing_cost");
    private String SEPRATOR = "_";

    @Override
    public double calculateJobCost(PrintingJob printingJob) {

        String defaultKey =  generateCostingKey(printingJob);
        double finalCost = 0.0;
        if(printingJob.getColourPages()>0){
            String finalKeyForCLR = defaultKey+PrintingColor.CLR;
            double costForCLR = Double.parseDouble(resourceBundle.getString(finalKeyForCLR)) * printingJob.getMonochromePages();
            finalCost += costForCLR;
        }
        if(printingJob.getMonochromePages()>0){
            String finalKeyForBW = defaultKey+PrintingColor.BW;
            double costForBW = Double.parseDouble(resourceBundle.getString(finalKeyForBW)) * printingJob.getMonochromePages();
            finalCost +=costForBW;
        }
        return finalCost;
    }

    private String generateCostingKey(PrintingJob printingJob){
        StringBuffer key = new StringBuffer();
        key.append(PaperType.A4);
        key.append(SEPRATOR);
        if(printingJob.isDoubleSided()){
            key.append(PrintinType.DOUBLE);
        }else {
            key.append(PrintinType.SINGLE);
        }
        key.append(SEPRATOR);
        return key.toString();

    }
}
