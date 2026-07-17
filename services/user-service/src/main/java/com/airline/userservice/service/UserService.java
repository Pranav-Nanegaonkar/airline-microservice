package com.airline.userservice.service;

import com.airline.commonlib.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(long id);

    Page<UserDTO> getAllUsers(Pageable pageable);

}