package com.paper;


import com.paper.model.InvoicedPrintingJob;
import com.paper.model.PrintingJob;

import java.util.List;


public interface JobProcessor {

    List<InvoicedPrintingJob> doProcess(List<PrintingJob> printingJobs);


}
