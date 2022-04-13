package com.example.labproject.web.controller;

import com.example.labproject.domain.both_service.BothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BothController {

    @Autowired
    private BothService bothService;

    @GetMapping("both/list")
    public String list(Model model) {
        model.addAttribute("both", bothService.getAllList());
        model.addAttribute("title", "Список пользователей с рассылками");

        return "both/list";
    }

    @GetMapping("both/list1")
    public String list1(Model model) {
        model.addAttribute("both1", bothService.getAllList1());
        model.addAttribute("title", "Список рассылок и входящих пользователей");

        return "both/list1";
    }
}
