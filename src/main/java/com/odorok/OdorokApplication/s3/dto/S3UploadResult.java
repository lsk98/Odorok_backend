package com.odorok.OdorokApplication.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class S3UploadResult {
    private String key;
    private String url;

}
