package com.project.tb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller
class MainController{
    @RequestMapping("/")
    public String getHello(Model model){
return "hello";
    }
}