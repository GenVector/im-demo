package pers.lujiayi.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("")
    public String indexHtml() {
        return "index";
    }

    @RequestMapping("/{file}.html")
    public String AccessHtml(@PathVariable String file) {
        return file;
    }

}
