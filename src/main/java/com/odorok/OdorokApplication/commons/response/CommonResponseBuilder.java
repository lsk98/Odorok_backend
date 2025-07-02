package com.odorok.OdorokApplication.commons.response;

public final class CommonResponseBuilder {
    private static final String SUCCESS = "success";
    private static final String IN_PROGRESS = "IN_PROGRESS";
    private static final String DONE = "DONE";

    //성공 응답
    public static <T> ResponseRoot<T> success(String message, T data) {
        return success(SUCCESS, message, data);
    }

    //성공 응답(no data)
    public static <T> ResponseRoot<T> success(String message) {
        return ResponseRoot.<T>builder().status(SUCCESS).message(message).build();
    }

    // 성공 응답 (with data, success 외 다른 상태)
    public static <T> ResponseRoot<T> success(String status, String message, T data) {
        return ResponseRoot.<T>builder().status(status).message(message).data(data).build();
    }

    public static <T> ResponseRoot<T> successInProgress(String message, T data) {
        return success(IN_PROGRESS, message, data);
    }

    public static <T> ResponseRoot<T> successDone(String message, T data) {
        return success(DONE, message, data);
    }

    // 실패 응답 생성
    public static <T> ResponseRoot<T> fail(String message) {
        return ResponseRoot.<T>builder().status("fail").message(message).build();
    }

    private CommonResponseBuilder() {}
}
