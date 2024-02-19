package com.example.bookdatarest.services.imple;

import com.example.bookdatarest.model.User;
import com.example.bookdatarest.record_.UserRecord;
import com.example.bookdatarest.repository.UserRepository;
import com.example.bookdatarest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

   /* public User recordUser(UserRecord userRecord){
        User newUser = User.builder()
                .firstName(userRecord.firstName())
                .lastName(userRecord.lastName())
                .email(userRecord.email())
                .build();

        return userRepository.save(newUser);
    }*/
    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(users -> new UserRecord(
                        users.getUser_id(),
                        users.getFirstName(),
                        users.getLastName(),
                        users.getEmail(),
                        users.getPassword()))
                .collect(Collectors.toList());
    }

    @Override
    public User add(User users) {
        Optional<User> users1 = userRepository.findByEmail(users.getEmail());
        if (users1.isPresent()){
            throw new RuntimeException("A user with " +users.getEmail()+ " already exists");
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    @Override
    public User getUsersByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Users not found"));
    }

    @Override
    public User update(User users) {
        users.setRole(users.getRole());
        return userRepository.save(users);
    }

    @Override
    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }
}
