package com.paper;

import com.paper.exception.CustomException;
import com.paper.model.InvoicedPrintingJob;
import com.paper.model.PrintingJob;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml"})
public class ServiceTest {

    private static String folderPath ;
    private static int maxPrintAllowed ;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("printpaper");
        folderPath= resourceBundle.getString("filepath");
        maxPrintAllowed = Integer.parseInt(resourceBundle.getString("max_print_allowed"));
    }


    @Autowired
    Invoice invoice;

    @Test(expected=CustomException.class)
    public void testInvalidFileName() {
        String fileName = folderPath+"ABCDE.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test(expected=CustomException.class)
    public void testEmptyFile() {
        String fileName = folderPath+"printjobs_Empty.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test(expected=CustomException.class)
    public void testMissingColumnFile() {
        String fileName = folderPath+"printjobsMIssingColumn.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test(expected=CustomException.class)
    public void testMissingRow() {
        String fileName = folderPath+"printjobsMIssingRow.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test(expected=CustomException.class)
    public void testNegativeValuesFile() {
        String fileName = folderPath+"printjobsNegativeValues.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test(expected=CustomException.class)
    public void testWrongBooleanFile() {
        String fileName = folderPath+"printjobsWrongBoolean.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test(expected=CustomException.class)
    public void testMaxPrint() {
        String fileName = folderPath+"printjobsGreaterThan1000.csv";
        invoice.getJobReader().getJobDetails(fileName);
    }

    @Test
    public void testSingleJobTenTenDoubleFile() {
        String fileName = folderPath+"printjobs_10_10_double.csv";
        List<PrintingJob> printingJobList = invoice.getJobReader().getJobDetails(fileName);
        Assert.assertEquals(printingJobList.size(), 1);
        List<InvoicedPrintingJob> invoicedPrintingJobs = invoice.getJobProcessor().doProcess(printingJobList);
        InvoicedPrintingJob invoicedPrintingJob = invoicedPrintingJobs.get(0);
        Assert.assertEquals(new BigDecimal(3).doubleValue(),invoicedPrintingJob.getTotalCost().doubleValue());

    }

    @Test
    public void testOnlyBW() {
        String fileName = folderPath+"printjobsOnlyBW.csv";
        List<PrintingJob> printingJobList = invoice.getJobReader().getJobDetails(fileName);
        List<InvoicedPrintingJob> invoicedPrintingJobs = invoice.getJobProcessor().doProcess(printingJobList);
        BigDecimal totalInvoicedAmount = BigDecimal.ZERO;
        for ( InvoicedPrintingJob invoicedPrintingJob: invoicedPrintingJobs){
            totalInvoicedAmount =totalInvoicedAmount.add(invoicedPrintingJob.getTotalCost());
        }
        Assert.assertEquals(new BigDecimal(59.6).doubleValue(), totalInvoicedAmount.doubleValue() );
    }

    @Test
    public void testOnlyColor() {
        String fileName = folderPath+"printjobsOnlyColor.csv";
        List<PrintingJob> printingJobList = invoice.getJobReader().getJobDetails(fileName);
        List<InvoicedPrintingJob> invoicedPrintingJobs = invoice.getJobProcessor().doProcess(printingJobList);
        BigDecimal totalInvoicedAmount = BigDecimal.ZERO;
        for ( InvoicedPrintingJob invoicedPrintingJob: invoicedPrintingJobs){
            totalInvoicedAmount =totalInvoicedAmount.add(invoicedPrintingJob.getTotalCost());
        }
        Assert.assertEquals(new BigDecimal(117.9).doubleValue(),totalInvoicedAmount.doubleValue());
    }




    @Test
    public void testSuccess() {
        String fileName = folderPath+"printjobs.csv";
        List<PrintingJob> printingJobList = invoice.getJobReader().getJobDetails(fileName);
        List<InvoicedPrintingJob> invoicedPrintingJobs = invoice.getJobProcessor().doProcess(printingJobList);
        BigDecimal totalInvoicedAmount = BigDecimal.ZERO;
        for ( InvoicedPrintingJob invoicedPrintingJob: invoicedPrintingJobs){
            totalInvoicedAmount =totalInvoicedAmount.add(invoicedPrintingJob.getTotalCost());
        }
        Assert.assertEquals(new BigDecimal(64.1).doubleValue(),totalInvoicedAmount.doubleValue());
    }


}
