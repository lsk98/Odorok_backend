package com.odorok.OdorokApplication.commons.response;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRoot<T> {
    private String status;  // "success" or "fail"
    private String message;
    private T data;
}