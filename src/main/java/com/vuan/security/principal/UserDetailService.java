package com.vuan.security.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vuan.model.User;
import com.vuan.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	//check user
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found -> user or password"+username));
		return UserPrincipal.build(user);
	}
}
