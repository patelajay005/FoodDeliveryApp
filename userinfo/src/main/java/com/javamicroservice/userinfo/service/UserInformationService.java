package com.javamicroservice.userinfo.service;

import com.javamicroservice.userinfo.dto.UserInformationDTO;
import com.javamicroservice.userinfo.entity.UserInformation;
import com.javamicroservice.userinfo.mapper.UserInformationMapper;
import com.javamicroservice.userinfo.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInformationService {

    @Autowired
    UserInformationRepository userInformationRepository;

    public List<UserInformationDTO> getUserDetails() {
        List<UserInformation> userInformations  = userInformationRepository.findAll();

        //Convert from Entity to DTOs
        return userInformations.stream()
                .map(UserInformationMapper.INFORMATION_MAPPER::userInformationEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<UserInformationDTO> addUser(UserInformationDTO userInformationDTO) {
        userInformationRepository.save(UserInformationMapper.INFORMATION_MAPPER.userInformationDTOToEntity(userInformationDTO));
        List<UserInformation> userInformations  = userInformationRepository.findAll();

        //Convert from Entity to DTOs
        return userInformations.stream()
                .map(UserInformationMapper.INFORMATION_MAPPER::userInformationEntityToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<UserInformationDTO> userDetailById(Integer id) {
            Optional<UserInformation> optionalUserInformation = userInformationRepository.findById(id);

        return optionalUserInformation.map(userInformation -> new ResponseEntity<>(UserInformationMapper.INFORMATION_MAPPER.userInformationEntityToDTO(userInformation), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new UserInformationDTO(), HttpStatus.NOT_FOUND));
    }
}