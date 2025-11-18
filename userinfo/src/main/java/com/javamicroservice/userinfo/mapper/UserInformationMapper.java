package com.javamicroservice.userinfo.mapper;

import com.javamicroservice.userinfo.dto.UserInformationDTO;
import com.javamicroservice.userinfo.entity.UserInformation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInformationMapper {

    UserInformationMapper INFORMATION_MAPPER = Mappers.getMapper(UserInformationMapper.class);

    UserInformation userInformationDTOToEntity(UserInformationDTO userInformationDTO);

    UserInformationDTO userInformationEntityToDTO(UserInformation userInformationDTO);
}
