package com.tistory.eclipse4j.app.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@SuppressWarnings({"unchecked", "rawtypes"})
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public long getTimestamp(){
        return new Date().getTime();
    }
    public static <T> ApiResponse<T> success(T t){
        return new ApiResponse("OK", "SUCCESS", t);
    }

    public static <T> ApiResponse<T> fail(){
        return new ApiResponse("FAIL", "FAIL", null);
    }
}
