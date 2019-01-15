package cn.bestws.app.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(){
        return "hello world";
    }
    @RequestMapping("/test")
    public String test(){
        createException();
        return "我是正常的";
    }

    private void createException(){
        int i = 5/0;
    }
}
