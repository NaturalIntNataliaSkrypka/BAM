package com.naturalint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skn on 10/9/2017.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome(ModelMap model) {
        model.put("message", "Hello world!");
        return "welcome";
    }
}
