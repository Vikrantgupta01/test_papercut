package com.paper.service;

import com.paper.JobReader;
import com.paper.exception.CustomException;
import com.paper.model.PrintingJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@Component("jobReader")
public class JobReaderImpl implements JobReader {

    static Logger log = Logger.getLogger(JobReaderImpl.class.getName());

    static private  String DEFAULT_PAPER_TYPE = "A4";

    @Autowired
    Validator validator;


    public List<PrintingJob> getJobDetails(String filePath) {

        //read file and get data[]
        //do basic validation of file data
        //parse information into object

        try {
            return getFileDate(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<PrintingJob> getFileDate(String csvFile ) throws IOException{
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<PrintingJob> printingJobs = new ArrayList<PrintingJob>();
        try {
            FileReader fileReader = new FileReader(csvFile);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                validator.validate(data);
                printingJobs.add(populateJobDetails(data));
            }

        } catch (FileNotFoundException e) {
            log.error("File not found "+csvFile);
            throw new CustomException("Invalid File Path");
        } catch (IOException e) {
            log.error("Job file is readable state " + e.getMessage());
            throw new CustomException("Job file is readable state ");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("Exception while closing the bufferreader");
                }
            }
        }
        return  printingJobs;
    }


    private PrintingJob populateJobDetails(String[] data) {

        int totalPages = Integer.parseInt(data[0].trim());
        int colourPages= Integer.parseInt(data[1].trim());
        boolean isDoubleSided= Boolean.parseBoolean(data[2].trim());

        return new PrintingJob(DEFAULT_PAPER_TYPE,totalPages,colourPages,isDoubleSided);
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
