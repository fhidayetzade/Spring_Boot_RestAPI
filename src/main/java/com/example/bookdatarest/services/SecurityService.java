package com.example.bookdatarest.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService {

    UserDetailsService userDetailsService();
}
