package com.example.labproject.web.controller;

import com.example.labproject.domain.both_service.BothService;
import com.example.labproject.domain.mail_service.MailService;
import com.example.labproject.domain.model.Both;
import com.example.labproject.domain.model.Mail;
import com.example.labproject.domain.model.User;
import com.example.labproject.domain.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private BothService bothService;

    @RequestMapping(path = "/mail-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Mail> mailFilter(@RequestParam(name = "q", required = false, defaultValue = "") String name) {
        return mailService.getMailByMailNameLike(name);
    }

    @RequestMapping(path = "/user-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> userFilter(@RequestParam(name = "q", required = false, defaultValue = "") String name) {
        return userService.getUserByMailNameLike(name);
    }

    @RequestMapping(path = "/both-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Both> bothFilter(@RequestParam(name = "q", required = false, defaultValue = "") String name) {
        return bothService.getAllListByNameLike(name);
    }
}
