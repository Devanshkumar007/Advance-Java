package com.capg.controller;

import com.capg.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final UserClient userClient;
    private final RestTemplate restTemplate;

    @GetMapping("/doctors")
    public String getDoctors(){
//        String users = restTemplate.getForObject("http://UserService/users", String.class);
        String users = userClient.getUsers();
        return "DOCTORS FETCHED caliing users -> " + users ;
    }

}
