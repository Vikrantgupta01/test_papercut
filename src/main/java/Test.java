import com.paper.Invoice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ResourceBundle;

/**
 * Created by pubmatic on 10/10/15.
 */
public class Test {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("fileinformation");

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Invoice invoice = (Invoice)context.getBean("invoice");
        invoice.generateInvoice(resourceBundle.getString("filepath"));
    }
}
