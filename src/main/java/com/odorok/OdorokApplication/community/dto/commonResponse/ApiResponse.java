package com.odorok.OdorokApplication.community.dto.commonResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;  // "success" or "fail"
    private String message;
    private T data;

    //성공 응답
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }

    //성공 응답(no data)
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>("success", message, null);
    }

    // 실패 응답 생성
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>("fail", message, null);
    }

}
