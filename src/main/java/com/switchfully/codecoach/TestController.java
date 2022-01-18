package com.switchfully.codecoach;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping()
    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    @ResponseStatus(HttpStatus.OK)
    String test() {
        return "Hello";
    }

}
