package com.chandu.serviceA.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/serviceA")
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static  final  String SERVICE_A = "serviceA";

    private static  final String BASE_URL = "http://localhost:8084/";

    @GetMapping
    @CircuitBreaker(name = SERVICE_A,fallbackMethod = "serviceAFallback")
    public String serviceA(){
        String url = BASE_URL+"serviceB";
        return restTemplate.getForObject(url, String.class);
    }

    public String serviceAFallback(Exception e){
        return "service A fallback method";
    }
}
