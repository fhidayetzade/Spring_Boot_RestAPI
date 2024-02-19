package com.example.bookdatarest.rest.model.request;

import lombok.Data;


@Data
public class UserDto {

    private String email;
    private String username;
    private String password;
    private String roles;
}
