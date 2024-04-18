package com.example5.cemakkaya.controller;

import com.example5.cemakkaya.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class StampController {


    @GetMapping("/hello")
    @RolesAllowed(Roles.Delete)
    ResponseEntity<String> getTest() {
      return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
