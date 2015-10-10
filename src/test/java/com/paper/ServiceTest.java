package com.paper;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ResourceBundle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml"})
public class ServiceTest {

    private static String folderPath ;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("fileinformation");
        folderPath= resourceBundle.getString("filepath");
    }



    @Autowired
    Invoice invoice;



    @Test
    public void test1() {

        String fileName = folderPath+"printjobs_5Jobs.csv";
        invoice.generateInvoice(fileName);

    }

    @Test
    public void test2() {

        String fileName = folderPath+"printjobs.csv";
        invoice.generateInvoice(fileName);

    }


}
