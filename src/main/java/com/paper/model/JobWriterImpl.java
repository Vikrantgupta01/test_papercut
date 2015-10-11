package com.paper.model;

import com.paper.JobWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("jobWriter")
public class JobWriterImpl implements JobWriter {


    @Override
    public void doWrite(List<PrintingJob> printingJobs) {
        double totalInvoicedAmount = 0.0;
        System.out.println("**************************************************************");

        for (PrintingJob printingJob: printingJobs){
            totalInvoicedAmount+=printingJob.getCost();
            System.out.println(printingJob.toString());
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total Invoiced Amount is ="+totalInvoicedAmount);
        System.out.println("**************************************************************");

    }
}
