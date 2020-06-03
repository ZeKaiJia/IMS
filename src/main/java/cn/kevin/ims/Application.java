package cn.kevin.ims;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/**
 * Information Management System
 *
 * @author kevin
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.kevin.ims.dao")
public class Application {
    public static void main(String[] args) {
        log.info("Programme start.");
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(JSON.toJSONString(beanDefinitionNames));
        for (String beanDefinitionName : beanDefinitionNames) {
            if (beanDefinitionName.contains("student") || beanDefinitionName.contains("subject") ||
                    beanDefinitionName.contains("score")) {
                System.out.println(beanDefinitionName);
            }
        }
        log.info("Programme end.");
    }
//    @EventListener({ApplicationReadyEvent.class})
//    public void applicationReadyEvent() {
//        System.out.println("应用已经准备就绪 ... 启动浏览器");
//        String url = "http://localhost:8080/index";
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
