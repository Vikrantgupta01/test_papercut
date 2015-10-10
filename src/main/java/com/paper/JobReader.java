package com.paper;

import com.paper.model.PrintingJob;

import java.util.List;


public interface JobReader {

    List<PrintingJob> getJobDetails(String filePath);
}
