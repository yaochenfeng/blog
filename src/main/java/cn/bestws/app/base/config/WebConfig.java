package cn.bestws.app.base.config;

import cn.bestws.app.base.handle.HandlerMethodReturnValueHandlerProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer, InitializingBean {
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> list = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newList = new ArrayList<>();
        if (null != list) {
            for (HandlerMethodReturnValueHandler valueHandler: list) {
                if (valueHandler instanceof RequestResponseBodyMethodProcessor) {
                    HandlerMethodReturnValueHandlerProxy proxy = new HandlerMethodReturnValueHandlerProxy(valueHandler);
                    newList.add(proxy);
                } else {
                    newList.add(valueHandler);
                }
            }
        }

        requestMappingHandlerAdapter.setReturnValueHandlers(newList);
    }
}
