package com.sprinboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        SpringApplication.run(App.class, args);
        
        //RestTemplate template = new RestTemplate();
        
        //return template.getForObject("", String.class);
        
    }
}
