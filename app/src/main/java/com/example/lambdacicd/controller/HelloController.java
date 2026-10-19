
package com.example.lambdacicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    // Returns a list of strings
    @GetMapping("/api/messages")
    public List<String> getMessages() {
        return List.of(
                "Welcome to Lambda CI/CD",
                "Spring Boot application deployed successfully",
                "Build pipeline executed",
                "Deployment completed"
        );
    }

    // Returns a list of users
    @GetMapping("/api/users")
    public List<Map<String, Object>> getUsers() {
        return List.of(
                Map.of(
                        "id", 1,
                        "name", "Rahul",
                        "role", "Developer",
                        "active", true
                ),
                Map.of(
                        "id", 2,
                        "name", "Priya",
                        "role", "DevOps Engineer",
                        "active", true
                ),
                Map.of(
                        "id", 3,
                        "name", "Amit",
                        "role", "Tester",
                        "active", false
                )
        );
    }

    // Returns application details
    @GetMapping("/api/info")
    public Map<String, Object> getApplicationInfo() {
        return Map.of(
                "application", "lambda-cicd-platform",
                "framework", "Spring Boot",
                "deployment", "AWS Lambda",
                "environment", "learning",
                "features", List.of(
                        "REST APIs",
                        "CI/CD pipeline",
                        "Automated deployment",
                        "Static data responses"
                )
        );
    }

    // Returns a list of products
    @GetMapping("/api/products")
    public List<Map<String, Object>> getProducts() {
        return List.of(
                Map.of(
                        "id", 101,
                        "name", "Laptop",
                        "price", 65000,
                        "category", "Electronics"
                ),
                Map.of(
                        "id", 102,
                        "name", "Keyboard",
                        "price", 2500,
                        "category", "Accessories"
                ),
                Map.of(
                        "id", 103,
                        "name", "Monitor",
                        "price", 18000,
                        "category", "Electronics"
                )
        );
    }

    // Returns a health/status response
    @GetMapping("/api/status")
    public Map<String, Object> getStatus() {
        return Map.of(
                "status", "UP",
                "service", "lambda-cicd-platform",
                "message", "Application is running successfully"
        );
    }
}
