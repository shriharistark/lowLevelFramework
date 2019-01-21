package com.lowLevelFrameworkTest.lowLevelFrameworkTest.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String throwAuthenticationResponse(){

        return "Hello. I don't recognize you. Are you from Earth?";
    }
}
