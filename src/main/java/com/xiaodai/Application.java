package com.xiaodai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Author ：dai
 * Date   ：2020/12/25 3:16 下午
 * Description：
 */
@SpringBootApplication(scanBasePackages = {"com.xiaodai"})
@EntityScan("com.xiaodai")
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
