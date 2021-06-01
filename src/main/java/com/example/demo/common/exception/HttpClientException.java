package com.example.demo.common.exception;


import com.example.demo.common.http.HttpStatus;
import io.netty.handler.codec.http.HttpMethod;

/**
 * @author huangyy@zjiec.com
 * @date 2020/06/04
 */
public class HttpClientException extends Exception{

    private final String url;

    private final HttpMethod httpMethod;

    private final HttpStatus httpStatus;

    public HttpClientException(String url, HttpMethod httpMethod, HttpStatus httpStatus) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.httpStatus = httpStatus;
    }

    public void printTrace() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        stringBuilder.append(": ").append(url);
        if (httpMethod != null) {
            stringBuilder.append(" ").append(httpMethod.name()).append("请求");
        }
        if (httpStatus != null) {
            stringBuilder.append("返回的状态码为: ").append(httpStatus.value());
        }
        System.err.println(stringBuilder.toString());
    }
}
