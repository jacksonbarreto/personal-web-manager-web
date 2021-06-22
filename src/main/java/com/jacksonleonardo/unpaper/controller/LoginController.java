package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.DTO.UserDTO;
import com.jacksonleonardo.unpaper.model.entities.Credential;
import com.jacksonleonardo.unpaper.model.entities.IUser;
import com.jacksonleonardo.unpaper.model.entities.User;
import com.jacksonleonardo.unpaper.model.repositories.UserRepository;
import com.jacksonleonardo.unpaper.model.services.*;
import com.jacksonleonardo.unpaper.model.valueObjects.Email;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;

import static com.jacksonleonardo.unpaper.model.enumerators.ERole.SIMPLE;
import static com.jacksonleonardo.unpaper.model.enumerators.EUserState.ACTIVE;

@Controller
public class LoginController {

    @GetMapping({"/index", ""})
    public ModelAndView start(UserDTO userDTO){
        return new ModelAndView("index");
    }

    @PostMapping("/index/login")
    public String login(UserDTO user){
        IAuthenticationService authenticationService = new AuthenticationService(IdentificationService.identificationServiceDefault(), PermissionService.permissionServiceDefault(), LoginService.LoginServiceDefault());
        if (authenticationService.authenticate(user.getEmail(), user.getPassword())) {
            return "/dashboard";
        }
        return "/index";
    }

    @GetMapping("/index/signUp")
    public ModelAndView nnew(UserDTO userDTO){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/index/signUp")
    public ModelAndView signUp(@Valid UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("index");
        }else {
            ModelAndView mv = new ModelAndView("dashboard");
            IUser user = new User(userDTO.getName(), new Credential("web", userDTO.getPassword()),
                    Collections.singletonList(ACTIVE),
                    Collections.singletonList(SIMPLE),
                    new Email(userDTO.getEmail()));

            UserRepository.getInstance().add(user);
            SessionService.addUserInSession(user);
            return mv;
        }

    }


    @GetMapping("/logout")
    public ModelAndView logout(UserDTO userDTO){
        SessionService.killSession();
        return new ModelAndView("redirect:/index");
    }
}
