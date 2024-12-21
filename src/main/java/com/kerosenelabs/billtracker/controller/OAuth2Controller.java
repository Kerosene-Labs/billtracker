package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.model.request.HandleTokenRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuth2Controller {
    @PostMapping("/oauth2/handleToken")
    public void handleToken(@RequestBody HandleTokenRequest request) {

    }
}
