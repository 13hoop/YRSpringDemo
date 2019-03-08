package BilibiliDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactorBean {


    public static UserService createUserService() {
        return new UserServiceImp();
    }

    public static UserService createUserServiceByJDKProxy() {

        // target-Class
        final UserService service = new UserServiceImp();

        // aspect-Class
        final MyAspect aspect = new MyAspect();

        /*  Aspect： combine target's ponitCut & advice
         *
         *
         *
         */

        UserService proxyService = (UserService)Proxy.newProxyInstance(MyFactorBean.class.getClassLoader(), new Class[]{ UserService.class }, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                aspect.beforUsing();
                Object obj = method.invoke(service, args);
                aspect.aferUsing();
                return obj;
            }
        });
        return proxyService;
    }
}
