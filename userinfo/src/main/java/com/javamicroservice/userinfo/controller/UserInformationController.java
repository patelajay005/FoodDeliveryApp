package com.javamicroservice.userinfo.controller;

import com.javamicroservice.userinfo.dto.UserInformationDTO;
import com.javamicroservice.userinfo.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInformationController {

    @Autowired
    UserInformationService userInformationService;


    @GetMapping("/userDetails")
    public ResponseEntity<List<UserInformationDTO>> getUserDetails() {
        List<UserInformationDTO> userInformationDTOS = userInformationService.getUserDetails();
        return new ResponseEntity<>(userInformationDTOS, HttpStatus.OK);
    }

    @PostMapping("/adduser")
    public ResponseEntity<List<UserInformationDTO>> addUser(@RequestBody UserInformationDTO userInformationDTO) {
        List<UserInformationDTO> restaurantDTOS = userInformationService.addUser(userInformationDTO);
        return new ResponseEntity<>(restaurantDTOS, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInformationDTO> userDetailById(@PathVariable Integer id) {
        return userInformationService.userDetailById(id);
    }
}
