package com.paper.service;

import com.paper.JobReader;
import com.paper.exception.CustomException;
import com.paper.model.PrintingJob;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("jobReader")
public class JobReaderImpl implements JobReader {

    private  static Logger log = Logger.getLogger(JobReaderImpl.class.getName());

    static private  String DEFAULT_PAPER_TYPE = "A4";

    @Autowired
    Validator validator;


    /**
     * @param filePath
     * @return List<PrintingJob>
     * @throws CustomException
     *
     * get the job data row file and do the data validation before setting data for processing
     *
     * */
    public List<PrintingJob> getJobDetails(String filePath)  {
        List<String[]> rows = null;
        try {
            rows = getFileDate(filePath);
        } catch (IOException e) {
            log.error("Error while fetching job details from file");
            throw new CustomException(e.getMessage());
        }

        validateJobsData(rows);
        return getPrintingJobDetails(rows);

    }

    /**
     * @param jobData
     * @throws CustomException
     * validate input data
     */
    private void validateJobsData(List<String[]> jobData) {
        List<String> errorMessages = new ArrayList<String>();
        for (String[] data:jobData ){
            validator.validate(data,errorMessages);
        }

        if(errorMessages.size()>0){
            throw  new CustomException(errorMessages.toString());
        }
    }

    private List<PrintingJob> getPrintingJobDetails(List<String[]> jobData) {
        List<PrintingJob> printingJobs = new ArrayList<PrintingJob>();
        for (String[] data:jobData ){
            printingJobs.add(populateJobDetails(data));
        }

       return printingJobs;
    }




    private List<String[]> getFileDate(String csvFile ) throws IOException{
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> printingJobs = new ArrayList<String[]>();

        try {
            FileReader fileReader = new FileReader(csvFile);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                printingJobs.add(data);
            }
            if (printingJobs.size()==0){
                throw  new CustomException("No data found to process");
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
