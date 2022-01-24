package com.cos.blog.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 빈으로 등록됨
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// JPA Naming 전략
	// SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?
	User findByUsernameAndPassword(String username, String password);
	
	@Query(value="SELECT * FROM USER WHERE USERNAME = ?1 AND PASSWORD = ?2",nativeQuery = true)
	User login(String username, String password);
	
	Optional<User> findByUsername(String username);
	
}
