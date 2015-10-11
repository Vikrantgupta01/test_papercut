package com.paper.service;

import com.paper.JobProcessor;
import com.paper.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component("jobProcessor")
public class JobProcessorImpl implements JobProcessor{

    private  static ResourceBundle resourceBundle = ResourceBundle.getBundle("printing_cost");
    private String SEPRATOR = "_";

    @Override
    public List<InvoicedPrintingJob> doProcess(List<PrintingJob> printingJobs) {
        List<InvoicedPrintingJob> invoicedPrintingJobs = new ArrayList<InvoicedPrintingJob>();
        for (PrintingJob printingJob: printingJobs){
           InvoicedPrintingJob invoicedPrintingJob =  calculateJobCost(printingJob);
            invoicedPrintingJobs.add(invoicedPrintingJob);
        }
        return invoicedPrintingJobs;
    }


    private InvoicedPrintingJob calculateJobCost(PrintingJob printingJob) {

        InvoicedPrintingJob invoicedPrintingJob = new InvoicedPrintingJob(printingJob);
        String defaultKey =  generateCostingKey(printingJob);
        BigDecimal finalCost =BigDecimal.ZERO;
        finalCost.setScale(2,BigDecimal.ROUND_DOWN);

        if(printingJob.getColourPages()>0){
            String finalKeyForCLR = defaultKey+PrintingColor.CLR;
            BigDecimal perUnitColorCost = new BigDecimal(resourceBundle.getString(finalKeyForCLR));
            BigDecimal colorPages =  new BigDecimal(printingJob.getColourPages());
            BigDecimal costForCLR = perUnitColorCost.multiply(colorPages);
            finalCost = finalCost.add(costForCLR);
            invoicedPrintingJob.setPerUnitColorPrint(perUnitColorCost);
            invoicedPrintingJob.setColorPrintCost(costForCLR);
        }
        if(printingJob.getMonochromePages()>0){
            String finalKeyForBW = defaultKey+PrintingColor.BW;
            BigDecimal perUnitMonoCost = new BigDecimal(resourceBundle.getString(finalKeyForBW));
            BigDecimal monoPages =  new BigDecimal(printingJob.getMonochromePages());
            BigDecimal costForMono = perUnitMonoCost.multiply(monoPages);
            finalCost = finalCost.add(costForMono);
            invoicedPrintingJob.setPerUnitMonoPrint(perUnitMonoCost);
            invoicedPrintingJob.setMonoPrintCost(costForMono);
        }
        invoicedPrintingJob.setTotalCost(finalCost);
        return  invoicedPrintingJob;
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
