package com.nia.services.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

   @RequestMapping({ "/reset*", "/login" })
   public String index() {
       return "forward:/index.html";
   }
}