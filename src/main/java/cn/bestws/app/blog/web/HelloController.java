package cn.bestws.app.blog.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(description = "测试")
@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @RequestMapping(value = "")
    public String index(){
        return "hello";
    }
}
