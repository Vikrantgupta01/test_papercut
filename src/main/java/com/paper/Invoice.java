package com.paper;

import com.paper.model.PrintingJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("invoice")
public class Invoice {

    @Autowired
    private JobReader jobReader;


    public JobReader getJobReader() {
        return jobReader;
    }

    public void setJobReader(JobReader jobReader) {
        this.jobReader = jobReader;
    }

    public void generateInvoice(String filePath)
    {
        List<PrintingJob> printingJobs = jobReader.getJobDetails(filePath);
        double totalInvoicedAmount = 0.0;

        for (PrintingJob printingJob: printingJobs){
            System.out.println("JOb");
            totalInvoicedAmount+=printingJob.getCost();
        }

        System.out.println(totalInvoicedAmount);


    }

}
