package com.example5.cemakkaya.mainpage;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class MainpageController {

    public MainpageController() {
    }

    @RequestMapping("/info")
    public String testText(
            @RequestParam(value = "name", defaultValue = "cem") String name
    ) {
        if (name.equals("cem")) {
            return name + " " + HttpStatus.OK.getReasonPhrase();
        } else
            return name + " " + HttpStatus.NOT_FOUND.getReasonPhrase();
    }

}
