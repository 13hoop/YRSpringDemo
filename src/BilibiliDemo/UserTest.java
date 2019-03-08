package BilibiliDemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        UserService service = (UserServiceImp) ctx.getBean("userServiceImp");
        service.descripUser();
    }

    // 直接生成类
    @Test
    public void demo001() {
        UserService service = MyFactorBean.createUserService();
        service.addUser();
        service.deleteUser();
    }

    // 动态代理类
    @Test
    public void demo002() {
        UserService service = MyFactorBean.createUserServiceByJDKProxy();
        service.addUser();
        service.deleteUser();
    }
}