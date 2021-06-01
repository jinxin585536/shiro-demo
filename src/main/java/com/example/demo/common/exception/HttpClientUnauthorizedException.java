package com.example.demo.common.exception;


import com.example.demo.common.http.HttpStatus;
import io.netty.handler.codec.http.HttpMethod;


/**
 * @author huangyy@zjiec.com
 * @date 2020/06/04
 */
public class HttpClientUnauthorizedException extends HttpClientException{

    public HttpClientUnauthorizedException(String url) {
        this(url, null);
    }

    public HttpClientUnauthorizedException(String url, HttpMethod httpMethod) {
        super(url, httpMethod, HttpStatus.UNAUTHORIZED);
    }
}
