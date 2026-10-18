package com.example.lambdacicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello from the Lambda CI/CD learning project!",
                "service", "lambda-cicd-platform",
                "version", "1.0.0"
        );
    }
}
