package com.app.controllers;

import com.app.models.binding.ChangePasswordBinding;
import com.app.models.binding.RegisterBinding;
import com.app.services.UserService;
import com.app.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by vilimir on 03.04.17.
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterBinding registerBinding){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterBinding registerBinding, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        userService.registerUser(registerBinding);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model, Principal principal){
        if (error != null){
            model.addAttribute("error", Constants.ERROR_INVALID_CREDENTIALS);
        }
        return "login";
    }

    @GetMapping("/profile")
    public String getProfilePage(){
        return "profile";
    }

    @PutMapping("/profile/change-password")
    public ResponseEntity changePassword(@RequestBody ChangePasswordBinding changePasswordBinding
            , Principal principal){
        if (this.userService.changePassword(principal.getName(),changePasswordBinding)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);

    }
}
