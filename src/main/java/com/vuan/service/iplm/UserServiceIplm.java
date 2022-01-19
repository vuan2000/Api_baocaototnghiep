package com.vuan.service.iplm;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vuan.dto.UserDTO;
import com.vuan.model.User;
import com.vuan.repository.UserRepository;
import com.vuan.service.UserService;

@Service
@Transactional
public class UserServiceIplm implements UserService {
	private UserRepository UserRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;

	public UserServiceIplm(com.vuan.repository.UserRepository userRepository, ModelMapper modelMapper,
			PasswordEncoder passwordEncoder) {
		super();
		UserRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public <S extends UserDTO> S add(S entity) {
        User User = UserRepository.save(convertToEntity(entity));
        entity.setId(User.getId());
        return entity;
    }

    @Override
    public <S extends UserDTO> S update(S entity) {
    	User User = UserRepository.save(convertToEntity(entity));
        entity.setId(User.getId());
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        UserRepository.deleteById(id);
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> classOptional = Optional.ofNullable(UserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found")));
        return convertToDTO(classOptional.get());
    }

    @Override
    public List<UserDTO> findAll() {
        return UserRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByName(String name, Pageable pageable) {
        Page<User> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = UserRepository.findAll(pageable);
        }else {
            pageClass = UserRepository.findByNameContaining(name, pageable);
        }
        return pageClass.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Long countFindByName(String name, Pageable pageable) {
        Page<User> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = UserRepository.findAll(pageable);
        }else {
            pageClass = UserRepository.findByNameContaining(name, pageable);
        }
        return (long) pageClass.getContent().size();
    }

    @Override
    public Long countFindAll() {
        return (long) UserRepository.findAll().size();
    }

    private UserDTO convertToDTO(User User) {
    	UserDTO UserDTO = modelMapper.map(User, UserDTO.class);
        return UserDTO;
    }
    
    private User convertToEntity(UserDTO userDTO) {
    	User user = modelMapper.map(userDTO, User.class);
    	user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }

	@Override
	public Boolean existsByUsername(String username) {
		return UserRepository.existsByUsername(username);
	}
}
