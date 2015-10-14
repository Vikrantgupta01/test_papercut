package com.paper;

import com.paper.exception.CustomException;
import com.paper.model.PrintingJob;

import java.util.List;


public interface JobReader {

    /**
     * @param filePath
     * @return List<PrintingJob>
     * @throws CustomException
     */
    List<PrintingJob> getJobDetails(String filePath);
}
