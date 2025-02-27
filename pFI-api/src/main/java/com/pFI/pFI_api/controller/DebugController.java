package com.pFI.pFI_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Debug controller to help diagnose security configuration issues.
* This controller provides endpoints that should be accessible without authentication.
*/
@RestController
@RequestMapping("/debug")
public class DebugController {

    /**
    * Simple health check endpoint that should be accessible without authentication.
    * @return A simple status message
    */
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Debug controller is working! The application is running correctly.");
    }
}

