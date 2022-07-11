package com.example.vetclinic.controller;

import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.entity.User;
import com.example.vetclinic.exception.UserAccountExistException;
import com.example.vetclinic.service.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegistrationController {
    UserService userService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest webRequest, Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                            HttpServletRequest request, Errors errors){
        try{
            User registered = userService.registerNewUserAccount(userDto);
        }catch (UserAccountExistException uaeEx){
            mav.addObject("message", "An account for that username/email already exists.");
        }

        return new ModelAndView("successRegister", "user", userDto);
    }
}
