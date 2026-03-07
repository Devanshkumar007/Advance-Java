package com.cap.BookStroreRest.Config;

import com.cap.BookStroreRest.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

//    @Bean
//    public User user(){
//        User user = new User();
//        user.setEmail("gab@gmail.com");
//        user.setUsername("Gab007");
//        user.setPassword("12345");
//        return user;
//    }




}
