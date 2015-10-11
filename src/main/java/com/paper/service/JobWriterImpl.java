package com.paper.service;

import com.paper.JobWriter;
import com.paper.model.PrintingJob;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("jobWriter")
public class JobWriterImpl implements JobWriter {


    @Override
    public void doWrite(List<PrintingJob> printingJobs) {
        BigDecimal totalInvoicedAmount = new BigDecimal(0.0);
        System.out.println("**************************************************************");

        for (PrintingJob printingJob: printingJobs){
            totalInvoicedAmount =totalInvoicedAmount.add(printingJob.getCost());
            System.out.println(printingJob.toString());
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total Invoiced Amount is ="+totalInvoicedAmount.doubleValue());
        System.out.println("**************************************************************");

    }
}
