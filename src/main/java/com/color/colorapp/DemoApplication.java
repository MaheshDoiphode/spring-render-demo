package com.color.colorapp;

import com.color.colorapp.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Scheduled(fixedRate = 300000)
    public void keepAlive() {
        RestTemplate restTemplate = new RestTemplate();

        //String response = restTemplate.getForObject("https://demo-spring-8h3e.onrender.com/hello", String.class);
//        String response = restTemplate.getForObject("http:localhost:8080/hello", String.class);
//        System.out.println("Response from /hello endpoint: " + response);
    }




}

@RestController
class Hello{
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}

