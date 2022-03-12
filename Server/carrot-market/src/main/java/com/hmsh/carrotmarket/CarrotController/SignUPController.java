package com.hmsh.carrotmarket.CarrotController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUPController {
    @RequestMapping(value = "/signup")
    public String signUpData(){
        return "index.html";
    }
}
