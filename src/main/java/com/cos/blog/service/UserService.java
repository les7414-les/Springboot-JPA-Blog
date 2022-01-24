package com.cos.blog.service;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.RoleType;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional
	public void save(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	@Transactional
	public User updateUser(User requestUser) {
			
		User user = userRepository.findById(requestUser.getId())
				.orElseThrow(() -> {
					return new IllegalArgumentException("사용자 찾기 실패 : 아이디를 찾을 수 없습니다.");
				}
		);
		
//		User user = userRepository.findByUsername(requestUser.getUsername())
//				.orElseThrow(() -> {
//					return new IllegalArgumentException("사용자 찾기 실패 : 아이디를 찾을 수 없습니다.");
//				}
//		);
		
		if(user.getOauth() == null || user.getOauth().equals("")) {
			String rawPassword = requestUser.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setEmail(requestUser.getEmail());
		}
		
		
		// 회원수정 함수 종료 시 서비스종료 트랜잭션 종료 commit이 자동으로 됩니다.
		// 영속화 된 user 객체의 변화가 감지되면 더티체킹이 되어 updqte문을 날려줌.
		return user;
	}
	
	@Transactional(readOnly = true)
	public User getFineUser(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
}
