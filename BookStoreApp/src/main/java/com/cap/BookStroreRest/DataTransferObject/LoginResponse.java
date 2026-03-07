package com.cap.BookStroreRest.DataTransferObject;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String email;
}
