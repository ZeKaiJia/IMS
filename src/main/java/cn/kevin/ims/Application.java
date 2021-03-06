package cn.kevin.ims;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Information Management System 启动类
 * Application
 * @Author: Kevin
 * @Date: 2020 /2/18 10:56 下午
 */
@Slf4j
@EnableSwagger2
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.kevin.ims.mapper")
public class Application {
    public static void main(String[] args) {
        log.info("Programme start.");
        SpringApplication.run(Application.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        System.out.println(JSON.toJSONString(beanDefinitionNames));
        log.info("Programme end.");
    }
}
