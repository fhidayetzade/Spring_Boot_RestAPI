package com.example.bookdatarest.services;

import com.example.bookdatarest.model.User;
import com.example.bookdatarest.record_.UserRecord;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {



    List<UserRecord> getAllUsers();
    User add(User users);
    User getUsersByEmail(String email);
    User update(User users);
    void delete(String email);
}
