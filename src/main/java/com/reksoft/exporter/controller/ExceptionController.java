package com.reksoft.exporter.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {

    @RequestMapping(value = {
            "/{path:^(?!report$|css$|js$|images$).*$}",
            "/{path:^(?!report$|css$|js$|images$).*$}/**"
    })
    public String redirect() {
        return "index";
    }
}