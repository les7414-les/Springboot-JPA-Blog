package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpTestController {
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("cos").password("12345").email("cos@nate.com").build();
		System.out.println(m.getId());
		m.setId(5000);
		System.out.println(m.getId());
		return "lombokTest 완료";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 :: "+m.getId() + ", username :: "+ m.getUsername()+ ", password :: "+ m.getPassword()+ ", email :: "+ m.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		//return "post 요청 :: " + text;
		return "post 요청 :: "+m.getId() + ", username :: "+ m.getUsername()+ ", password :: "+ m.getPassword()+ ", email :: "+ m.getEmail();
	}
	
	@PostMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 :: "+m.getId() + ", username :: "+ m.getUsername()+ ", password :: "+ m.getPassword()+ ", email :: "+ m.getEmail();
	}
	
	@PostMapping("/http/delete")
	public String deleteTest() {
		return " delete 요청";
	}
}
