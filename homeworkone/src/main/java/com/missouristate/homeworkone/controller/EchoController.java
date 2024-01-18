package com.missouristate.homeworkone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Keegan Spell
@Controller
public class EchoController {

    @GetMapping("/echo") public String getEcho() {
        return "echo";
    }
    @ResponseBody
    @GetMapping("/echomessage") public String getEchomessage(String message) {
        return message;
    }

}
