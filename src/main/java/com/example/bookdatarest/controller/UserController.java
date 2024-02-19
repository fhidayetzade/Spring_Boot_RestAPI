package com.example.bookdatarest.controller;

import com.example.bookdatarest.model.User;
import com.example.bookdatarest.record_.UserRecord;
import com.example.bookdatarest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/all")
    public ResponseEntity<List<UserRecord>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User users){
        return ResponseEntity.ok(userService.add(users));
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable("email") String email){
        return userService.getUsersByEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(User users){
        return ResponseEntity.ok(userService.update(users));
    }

    @DeleteMapping("/{email}")
    public void delete (@PathVariable("email") String email){
        userService.delete(email);
    }
}
