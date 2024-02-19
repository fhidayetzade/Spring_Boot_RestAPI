package com.example.bookdatarest.jwt;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTAuthenticationRequest {

    String userName;
    String password;

}
