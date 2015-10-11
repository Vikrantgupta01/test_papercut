package com.paper;


import com.paper.model.InvoicedPrintingJob;
import com.paper.model.PrintingJob;

import java.util.List;


public interface JobWriter {

    void doWrite(List<InvoicedPrintingJob> printingJobs);


}
