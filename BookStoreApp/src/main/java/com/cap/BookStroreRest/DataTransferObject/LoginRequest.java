package com.cap.BookStroreRest.DataTransferObject;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password ;
}
