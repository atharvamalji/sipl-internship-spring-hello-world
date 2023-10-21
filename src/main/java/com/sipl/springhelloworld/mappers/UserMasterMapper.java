package com.sipl.springhelloworld.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sipl.springhelloworld.dtos.UserMasterDto;
import com.sipl.springhelloworld.entities.UserMaster;

@Mapper
public interface UserMasterMapper {
    UserMaster mapUserMasterDtoToUserMaster(UserMasterDto userMasterDto);
    UserMasterDto mapUserMasterToUserMasterDto(UserMaster userMaster);
    List<UserMasterDto> mapListUserMasterToListUserMasterDto(List<UserMaster> userMasterList);
}
