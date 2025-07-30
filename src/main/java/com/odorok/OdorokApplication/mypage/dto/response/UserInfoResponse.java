package com.odorok.OdorokApplication.mypage.dto.response;

import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String userProfilePictureUrl;
    private String userName;
    private String userTier;
    private Long userActivityScore;
}
