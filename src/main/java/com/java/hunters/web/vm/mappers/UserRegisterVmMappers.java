package com.java.hunters.web.vm.mappers;

import com.java.hunters.domain.User;
import com.java.hunters.web.vm.LoginVM;
import com.java.hunters.web.vm.response.UserResponse;
import com.java.hunters.web.vm.UserVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRegisterVmMappers {
    @Mapping(target = "joinDate", expression = "java(java.time.LocalDateTime.now())")

    User toEntity(UserVM userVM);

    List<UserResponse> toUserResponse(Page<User> users);

    User toUser(LoginVM loginVM);

}
