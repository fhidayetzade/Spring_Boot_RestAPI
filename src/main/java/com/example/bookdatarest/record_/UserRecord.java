package com.example.bookdatarest.record_;

public record UserRecord(
        Integer user_id,
        String firstName,
        String lastName,
        String email,
        String password) {
}
