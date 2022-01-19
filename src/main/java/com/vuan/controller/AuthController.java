package com.vuan.controller;

import javax.validation.Valid;

import com.vuan.security.JwtTokenProvider;
import com.vuan.security.principal.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuan.dto.UserDTO;
import com.vuan.dto.request.ResponseMessage;
import com.vuan.dto.request.SigninForm;
import com.vuan.dto.request.SignupForm;
import com.vuan.dto.response.JwtResponse;
import com.vuan.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = -1)
public class AuthController {
	
	private UserService userService;
	private ModelMapper modelMapper;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;

	public AuthController(UserService userService, ModelMapper modelMapper, AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider) {
		super();
		this.userService = userService;
		this.modelMapper = modelMapper;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> register(@RequestBody SignupForm signupForm) {
		 if(userService.existsByUsername(signupForm.getUsername())) {
			 return new ResponseEntity<>(new ResponseMessage("The username existed! Please ty again!"), HttpStatus.CONFLICT);
		 }
		 UserDTO userDTO = modelMapper.map(signupForm, UserDTO.class);
		 userService.add(userDTO);
		 
		 return new ResponseEntity<>(new ResponseMessage("Create user success"), HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> register(@RequestBody SigninForm signupForm) {
		 Authentication authentication = 
				 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signupForm.getUsername(), signupForm.getPassword()));
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 String token = jwtTokenProvider.createToken(authentication);
		 UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		 return new ResponseEntity<>(new JwtResponse(token, userPrincipal.getName(), userPrincipal.getRole()), HttpStatus.OK);
	}
}
