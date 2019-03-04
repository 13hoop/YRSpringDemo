import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppliactionMain {

    public static void main(String [] args) {
        String xmlPath = "beans.xml";
        ApplicationContext cxt = new ClassPathXmlApplicationContext(xmlPath);
        Knight kk = cxt.getBean(Knight.class);
        kk.embarkOnQuest();
    }
}
