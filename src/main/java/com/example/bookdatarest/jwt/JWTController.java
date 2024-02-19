package com.example.bookdatarest.jwt;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class JWTController {

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String getForAuthenticationUser (@RequestBody JWTAuthenticationRequest jwtAuthenticationRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        jwtAuthenticationRequest.getUserName(),
                        jwtAuthenticationRequest.getPassword()));
        if(authentication.isAuthenticated()){
          return   jwtService.generateToke(jwtAuthenticationRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid Username");
        }
    }


}
