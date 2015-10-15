package com.paper;


import com.paper.model.InvoicedPrintingJob;

import java.util.List;


public interface JobWriter {

    void doWrite(List<InvoicedPrintingJob> printingJobs);


}
