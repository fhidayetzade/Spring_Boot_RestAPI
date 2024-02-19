package com.example.bookdatarest.secuirty;

import com.example.bookdatarest.jwt.JWTAuthenticationFilter;
import com.example.bookdatarest.services.SecurityService;
import com.example.bookdatarest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class LibrarySecurityConfig {


    @Autowired
    private final    JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private final LibraryUserDetailsService userDetailsService;

  private final static String SECURED_URLs [] = {
          "/books/delete/{id}",
          "/books/addBooks"};
  private final static String UN_SECURED_URLs [] = {
          "/books/book/{id}",
          "/books/all",
          "/users/**",
          "/booksMapper/listAll",
          "/booksMapper/addBook",
          "/booksMapper/delete/{id}",
          "/categories/**",
          "/authentication/**"
  };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(x ->
                                x.requestMatchers(UN_SECURED_URLs).permitAll()
                                        .requestMatchers(SECURED_URLs).authenticated()
                        ).sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider()).addFilterBefore(
                                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                        ).build();




    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
}


}

/*
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(securityService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    } */


    /*@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }*/
/*.formLogin(AbstractHttpConfigurer::disable)
                        .httpBasic(Customizer.withDefaults()).build();*/
 /*

                        ).formLogin(AbstractHttpConfigurer::disable)
                        .httpBasic(Customizer.withDefaults()).build();*/
                /*http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(request ->request.requestMatchers((UN_SECURED_URLs))
                                .permitAll()
                                .requestMatchers("/books/addBook").hasAnyAuthority("ADMIN")
                                .requestMatchers("/users/all").hasAnyAuthority("USER")
                                .anyRequest().authenticated()).build();*/


      /*  http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher(SECURED_URLs) // map current config to given resource path
                .sessionManagement(sessionManagementConfigurer
                        -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(SECURED_URLs).permitAll()
                        .requestMatchers(UN_SECURED_URLs).hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                ).build();*/

      /*  http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll().and()
                .authorizeHttpRequests().requestMatchers(SECURED_URLs)
                .hasAuthority("ADMIN").anyRequest()
                .authenticated().and().httpBasic().and().build();*/
