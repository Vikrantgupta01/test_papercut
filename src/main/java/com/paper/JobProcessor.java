package com.paper;


import com.paper.model.PrintingJob;

import java.util.List;


public interface JobProcessor {

    void doProcess(List<PrintingJob> printingJobs);


}
