package com.paper;

import com.paper.model.InvoicedPrintingJob;
import com.paper.model.PrintingJob;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("invoice")
public class Invoice {

    private static Logger log = Logger.getLogger(Invoice.class.getName());

    @Autowired
    private JobReader jobReader;

    @Autowired
    private JobProcessor jobProcessor;

    @Autowired
    private JobWriter jobWriter;


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
        log.info("Printing Job Start");
        List<PrintingJob> printingJobs = jobReader.getJobDetails(filePath);
        log.info("Total printing job are " + printingJobs.size());
        List<InvoicedPrintingJob> invoicedPrintingJobs= jobProcessor.doProcess(printingJobs);
        jobWriter.doWrite(invoicedPrintingJobs);
        log.info("Printing Job complete");
    }

}
