package cn.bestws.core.result;

public class ApiException extends RuntimeException {
    private Integer code;

    public ApiException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
