package org.contoso.messagerouterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MessageRouterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageRouterServiceApplication.class, args);
    }

}
