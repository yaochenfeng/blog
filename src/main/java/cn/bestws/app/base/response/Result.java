package cn.bestws.app.base.response;

import lombok.Data;

@Data
public class Result<T> {
    /*是否成功*/
    private Boolean success;
    /*操作状态码*/
    private Integer code;
    /*错误消息*/
    private String msg;
    /*具体的内容*/
    private T data;
}
