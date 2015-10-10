import com.paper.Invoice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ResourceBundle;

/**
 * Created by pubmatic on 10/10/15.
 */
public class Test {
    private static String folderPath ;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("fileinformation");
        folderPath= resourceBundle.getString("filepath");
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Invoice invoice = (Invoice)context.getBean("invoice");
        invoice.generateInvoice(folderPath+"printjobs.csv");
    }
}
