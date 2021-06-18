package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.DTO.UserDTO;
import com.jacksonleonardo.unpaper.model.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @PostMapping("/index/login")
    public String login(UserDTO user){
        IAuthenticationService authenticationService = new AuthenticationService(IdentificationService.identificationServiceDefault(), PermissionService.permissionServiceDefault(), LoginService.LoginServiceDefault());
        if (authenticationService.authenticate(user.getEmail(), user.getPassword()))
            return "/dashboard";
        return "/index";
    }

    @PostMapping("/index/signUp")
    public String signUp(){

        return "/index";
    }
}
