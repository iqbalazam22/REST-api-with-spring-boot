package com.iqbal.store.mappers;

import com.iqbal.store.dtos.RegisterUserRequest;
import com.iqbal.store.dtos.UpdateUserRequest;
import com.iqbal.store.dtos.UserDto;
import com.iqbal.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring"/*, unmappedTargetPolicy = ReportingPolicy.ERROR*/)
public interface UserMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "name", target = "name")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest registerUserRequest);
    void update(UpdateUserRequest request,@MappingTarget User user);
}
/*
    userDto has three variables and user has a lot of variables when we provide it to mappers we give user to userDto and
    it maps to userDto and userDto provides only three variables defined in DTO class
*/