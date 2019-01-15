package cn.bestws.app.base.handle;

import cn.bestws.app.base.util.ResultUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler proxyObject;

    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {

        proxyObject.handleReturnValue(ResultUtil.success(returnValue), returnType, mavContainer, webRequest);
    }

    private static final String STATUS_CODE_SUCCEEDED = "200";
}
