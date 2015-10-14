package com.paper.service;

import com.paper.JobWriter;
import com.paper.model.InvoicedPrintingJob;
import com.paper.model.PrintingJob;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("jobWriter")
public class JobWriterImpl implements JobWriter {

    private static String currency = "AUD";
    @Override
    public void doWrite(List<InvoicedPrintingJob> printingJobs) {
        BigDecimal totalInvoicedAmount = new BigDecimal(0.0);
        System.out.println("**************************************************************");
        int count=1;
        for (InvoicedPrintingJob invoicedPrintingJob: printingJobs){

            PrintingJob job = invoicedPrintingJob.getPrintingJob();

            System.out.println("-------------------------------------------------------------");
            System.out.println("Job Id "+count++);
            System.out.println("Total No of Pages\t\t=\t\t\t\t" + invoicedPrintingJob.getPrintingJob().getTotalPages());

            if(job.getColourPages()>0)
                System.out.println("Charges of color pages\t=\t\t\t\t" + invoicedPrintingJob.getColorPrintCost()+currency
                        +"  ("+job.getColourPages() + "X" + invoicedPrintingJob.getPerUnitColorPrint()+")");

            if(job.getMonochromePages()>0)
                System.out.println("Charges of b&w pages\t=\t\t\t\t" + invoicedPrintingJob.getMonoPrintCost() + currency +
                        "  (" + job.getMonochromePages() + "X" + invoicedPrintingJob.getPerUnitMonoPrint() + ")");

            System.out.print("Total Cost\t\t\t\t=\t\t\t\t" + invoicedPrintingJob.getTotalCost() + currency);

            if (job.getColourPages()>0 && job.getMonochromePages()>0)
                System.out.println( "  (" + invoicedPrintingJob.getColorPrintCost() + "+"
                        + invoicedPrintingJob.getMonoPrintCost()+")");

            System.out.println("");

            totalInvoicedAmount =totalInvoicedAmount.add(invoicedPrintingJob.getTotalCost());

        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total Invoiced Amount is=\t\t\t\t"+totalInvoicedAmount.doubleValue()+currency);
        System.out.println("**************************************************************");

    }
}
