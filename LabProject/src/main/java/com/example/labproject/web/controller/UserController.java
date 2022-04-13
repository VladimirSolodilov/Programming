package com.example.labproject.web.controller;

import com.example.labproject.domain.user_service.UserService;
import com.example.labproject.web.form.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BothController bothController;

    @GetMapping("/user/registration")
    public ModelAndView registrationUser(ModelAndView modelAndView) {
        modelAndView.addObject("registrationForm", new UserRegistrationForm());
        modelAndView.setViewName("/user/registration");
        return modelAndView;
    }

    @PostMapping("/user/registration")
    public String registrationMailPost(Model model, UserRegistrationForm userRegistrationForm) {
        model.addAttribute(userService.setUserList(
                userRegistrationForm.getMailName(), userRegistrationForm.getLogin(),
                userRegistrationForm.getFirstName(), userRegistrationForm.getLastName()));

        return bothController.list1(model);
    }

    @GetMapping("/user/delete")
    public ModelAndView deleteUser(ModelAndView modelAndView) {
        modelAndView.addObject("deleteForm", new UserRegistrationForm());
        modelAndView.setViewName("/user/delete");
        return modelAndView;
    }

    @PostMapping("/user/delete")
    public String deleteUserPost(Model model, UserRegistrationForm userRegistrationForm) {
        model.addAttribute(userService.deleteUserList(userRegistrationForm.getLogin()));
        return bothController.list1(model);
    }

}
