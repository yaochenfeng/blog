package cn.bestws.app.blog.commom;

import lombok.Data;

/**
 * API返回值对象
 * @param <T> 返回的数据类型
 */
@Data
public class ApiResult<T> {
    enum ApiResultCode {

        /** 成功 */
        OK,

        /**
         * 失败
         */
        Failed,

        /**
         * 未找到资源
         */
        NotFound,

        /**
         * 未授权
         */
        NotAuth
    }
    /*是否操作成功*/
    private Boolean success;
    /**
     * 状态码
     */
    private ApiResultCode code;
    /**
     * 返回的数据
     */
    private T data;
    /**
     * 返回错误信息
     */
    private String errmsg;
}
