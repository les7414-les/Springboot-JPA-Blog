package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.repository.RoleType;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyTestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) throws Exception {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. id :: " + id + " 해당아이디는 데이터가 없습니다.";
		}
		
		return "삭제되었습니다. id :: "+ id;
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) throws IllegalAccessException {
		System.out.println("id ::: " + id);
		System.out.println("password ::: " + requestUser.getPassword());
		System.out.println("email ::: " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalAccessException("사용자정보 수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//userRepository.save(requestUser);
		
		return null;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pageUsers = userRepository.findAll(pageable);
		List<User> users = pageUsers.getContent();
		return users;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id :: " + user.getId());
		System.out.println("username :: " + user.getUsername());
		System.out.println("password :: " +user.getPassword());
		System.out.println("email :: " + user.getEmail());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) throws IllegalAccessException  {
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalAccessException>() {
			@Override
			public IllegalAccessException get() {				
				return new IllegalAccessException("해당유저는 없습니다. id :: " + id);
			}
		});
		return user;
	}
	
}
