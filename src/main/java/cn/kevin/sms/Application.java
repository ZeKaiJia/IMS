package cn.kevin.sms;



import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * @author Sean Wu
 */
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) throws Exception {
        log.info("程序启动啦啦啦");
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(JSON.toJSONString(beanDefinitionNames));

        log.info("程序启动完成啦啦啦");
    }
}
