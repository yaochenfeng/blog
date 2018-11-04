package cn.bestws.core.util;
import cn.bestws.core.result.Result;

public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
}
