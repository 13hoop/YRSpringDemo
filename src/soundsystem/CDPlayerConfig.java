package soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan  // <--- 此注解以包为界限，让Spring启动组件扫描，当然也可以用xml来启动
public class CDPlayerConfig {

}


