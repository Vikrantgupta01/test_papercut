package com.paper;

import com.paper.model.PrintingJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("invoice")
public class Invoice {

    @Autowired
    private JobReader jobReader;

    @Autowired
    private JobProcessor jobProcessor;


    public JobReader getJobReader() {
        return jobReader;
    }

    public void setJobReader(JobReader jobReader) {
        this.jobReader = jobReader;
    }

    public JobProcessor getJobProcessor() {
        return jobProcessor;
    }

    public void setJobProcessor(JobProcessor jobProcessor) {
        this.jobProcessor = jobProcessor;
    }

    public void generateInvoice(String filePath)
    {
        List<PrintingJob> printingJobs = jobReader.getJobDetails(filePath);
        double totalInvoicedAmount = 0.0;

        for (PrintingJob printingJob: printingJobs){
            totalInvoicedAmount+=jobProcessor.calculateJobCost(printingJob);
        }

        System.out.println(totalInvoicedAmount);


    }

}
