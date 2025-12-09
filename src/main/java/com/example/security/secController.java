package com.example.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class secController {
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the secured application!";
    }

    @GetMapping("/secureContent")
    public String secureContent() {
        return "This is secure content accessible only to authenticated users.";
    }

    @GetMapping("/confidentialData")
    public String confidentialData() {
        return "This is confidential data accessible only to authenticated users.";
    }
}
