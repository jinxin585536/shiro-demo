package com.example.demo.common.http;

/**
 * @author huangyy@zjiec.com
 * @date 2019-09-08
 */
public enum HttpStatus {

    /**
     * 接口成功调用，无任何错误
     */
    OK(200, "success"),

    /**
     * 接口接收的参数数据格式/数据内容无效
     */
    BAD_REQUEST(400, "接口接收的参数数据格式/数据内容无效"),

    /**
     * 接口接收的数据有效，但系统执行有问题
     */
    INTERNAL_SERVER_ERROR(500, "接口接收的数据有效，但系统执行有问题"),

    /**
     * 未登录/无授权
     */
    UNAUTHORIZED(401, "未登录/无授权"),

    /**
     * 未发现404（一般不用）
     */
    NOT_FOUND(404, "未发现404");

    private final int value;

    private final String description;

    HttpStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static HttpStatus getByStatusCode(int code) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.value() == code) {
                return status;
            }
        }
        return null;
    }

    public int value() {
        return this.value;
    }

    public String description() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
