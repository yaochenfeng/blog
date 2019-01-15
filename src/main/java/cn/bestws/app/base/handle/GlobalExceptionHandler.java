package cn.bestws.app.base.handle;

import cn.bestws.app.base.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public Object allExceptionHandler(HttpServletRequest request,
                                      Exception exception) throws Exception
    {
        exception.printStackTrace();
        log.error("异常："+exception.getLocalizedMessage());
        return ResultUtil.error(500,exception.getMessage());
    }
}