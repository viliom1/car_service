package com.app.controllers;

import com.app.models.binding.ContactUsBinding;
import com.app.utils.Mail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;


/**
 * Created by vilimir on 29.03.17.
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String getHomePage(Principal principal, Model model, HttpSession httpSession) {
        if (principal !=null) {
            model.addAttribute("username",principal.getName());
            httpSession.setAttribute("username",principal.getName());
        }
        return "index";
    }

    @GetMapping("/mechanics")
    public String getMechanicsPage(){
        return "mechanics";
    }

    @GetMapping("/pricing")
    public String getPricingPage(){
        return "pricing";
    }

    @GetMapping("/contact")
    public String getContactsPage(){
        return "contact";
    }

    @PostMapping("/contact")
    public String sendMessageToUs(@ModelAttribute ContactUsBinding contactUsBinding){
        Mail.sendFromGMail("yourcarserviceapp@gmail.com"
                ,String.format("Contact us message from %s",contactUsBinding.getEmail())
                ,String.format("%s %n%s %s%n%s %n%n%n%s "
                        ,contactUsBinding.getEmail()
                        ,contactUsBinding.getFirstName()
                        ,contactUsBinding.getLastName()
                        ,contactUsBinding.getPhone()
                        ,contactUsBinding.getMessage()));

        return "redirect:/";
    }
}
