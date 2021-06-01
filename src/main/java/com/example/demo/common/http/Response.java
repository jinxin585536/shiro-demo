package com.example.demo.common.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author huangyy@zjiec.com
 * @date 2020/10/28
 */
public final class Response {

    public static ResponseBuilder ok() {
        return new InnerResponse(HttpStatus.OK);
    }

    public static ResponseBuilder bad() {
        return new InnerResponse(HttpStatus.BAD_REQUEST);
    }

    public static ResponseBuilder err() {
        return new InnerResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseBuilder successOrFailed(boolean res) {
        if (res) {
            return ok();
        }
        return bad();
    }

    public interface ResponseBuilder {
        ResponseBuilder msg(String message);

        ResponseBuilder body(Object body);

        ResponseEntity<ResponseData> build();
    }

    private static class InnerResponse implements ResponseBuilder {
        private HttpStatus httpStatus;

        private Object body;

        private String message;

        private InnerResponse(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        @Override
        public ResponseBuilder body(Object body) {
            this.body = body;
            return this;
        }

        @Override
        public ResponseBuilder msg(String message) {
            this.message = message;
            return this;
        }

        @Override
        public ResponseEntity<ResponseData> build() {
            return ResponseEntity.status(httpStatus).body(new ResponseData(httpStatus.value(), body, message));
        }
    }
}
