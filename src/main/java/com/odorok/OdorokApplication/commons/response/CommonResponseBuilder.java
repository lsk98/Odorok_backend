package com.odorok.OdorokApplication.commons.response;

public final class CommonResponseBuilder {
    //성공 응답
    public static <T> ResponseRoot<T> success(String message, T data) {
        return ResponseRoot.<T>builder().status("success").message(message).data(data).build();
    }

    //성공 응답(no data)
    public static <T> ResponseRoot<T> success(String message) {
        return ResponseRoot.<T>builder().status("success").message(message).build();
    }

    // 실패 응답 생성
    public static <T> ResponseRoot<T> fail(String message) {
        return ResponseRoot.<T>builder().status("fail").message(message).build();
    }

    private CommonResponseBuilder() {}
}
