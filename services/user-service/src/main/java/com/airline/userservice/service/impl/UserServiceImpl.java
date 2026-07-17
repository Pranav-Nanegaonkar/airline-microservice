package com.airline.userservice.service.impl;

import com.airline.commonlib.dto.UserDTO;
import com.airline.commonlib.exception.ApiException;
import com.airline.userservice.entity.User;
import com.airline.userservice.mapper.UserMapper;
import com.airline.userservice.repository.UserRepository;
import com.airline.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                ApiException.badRequest("User with email not exists"));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                ApiException.badRequest("User with id not exists"));
        return UserMapper.toDTO(user);
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> all = userRepository.findAll(pageable);
        return all.map(UserMapper::toDTO);
    }
}
