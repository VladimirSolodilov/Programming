package com.example.labproject.web.controller;

import com.example.labproject.domain.mail_service.MailService;
import com.example.labproject.web.form.MailChangeForm;
import com.example.labproject.web.form.MailDeleteForm;
import com.example.labproject.web.form.MailRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/index")
    public String index(Model model) {
        return "/index";
    }

    @GetMapping("/mail/list")
    public String list(Model model) {
        model.addAttribute("mail", mailService.getMailList());
        model.addAttribute("title", "Список почтовых рассылок");

        return "/mail/list";
    }

    @GetMapping("/mail/registration")
    public ModelAndView registrationMail(ModelAndView modelAndView) {
        modelAndView.addObject("mailForm", new MailRegistrationForm());
        modelAndView.setViewName("/mail/registration");

        return modelAndView;
    }

    @PostMapping("/mail/registration")
    public String registrationMailPost(Model model, MailRegistrationForm mailRegistrationForm) {
        model.addAttribute(mailService.setMailList(mailRegistrationForm.getMailName(), mailRegistrationForm.getMailDescription()));

        return list(model);
    }

    @GetMapping("/mail/delete")
    public ModelAndView deleteMail(ModelAndView modelAndView) {
        modelAndView.addObject("deleteForm", new MailDeleteForm());
        modelAndView.setViewName("/mail/delete");

        return modelAndView;
    }

    @PostMapping("/mail/delete")
    public String deleteMailPost(Model model, MailDeleteForm mailDeleteForm) {
        model.addAttribute(mailService.deleteMailList(mailDeleteForm.getMailName()));

        return list(model);
    }

    @GetMapping("/mail/change")
    public ModelAndView changeMail(ModelAndView modelAndView) {
        modelAndView.addObject("changeForm", new MailChangeForm());
        modelAndView.setViewName("/mail/change");

        return modelAndView;
    }

    @PostMapping("/mail/change")
    public String changeMailPost(Model model, MailChangeForm mailChangeForm) {
        model.addAttribute(mailService.changeMail(mailChangeForm.getOldMailName(),
                mailChangeForm.getNewMailName()));

        return list(model);
    }
}
