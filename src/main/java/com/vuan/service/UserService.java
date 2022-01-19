package com.vuan.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vuan.dto.UserDTO;

public interface UserService {
	<S extends UserDTO> S add(S entity);

    <S extends UserDTO> S update(S entity);

    void deleteById(Long id);
    
    Boolean existsByUsername(String username);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    List<UserDTO> findByName(String name, Pageable pageable);

    Long countFindAll();

    Long countFindByName(String name, Pageable pageable);
}
