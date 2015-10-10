package com.paper.service;

import com.paper.JobReader;
import com.paper.model.PrintingJob;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component("jobReader")
public class JobReaderImpl implements JobReader {

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
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                printingJobs.add(populateJobDetails(data));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  printingJobs;

    }


    private PrintingJob populateJobDetails(String[] data) {

        int totalPages = Integer.parseInt(data[0].trim());
        int colourPages= Integer.parseInt(data[1].trim());
        boolean isDoubleSided= Boolean.parseBoolean(data[2].trim());
        return new PrintingJob(totalPages,colourPages,isDoubleSided);
    }


}
