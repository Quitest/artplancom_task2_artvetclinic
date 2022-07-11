package com.example.vetclinic.service;

import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.entity.User;
import com.example.vetclinic.exception.UserAccountExistException;

public interface IUserService {
    User registerNewUserAccount(UserDto userDto) throws UserAccountExistException;
}
