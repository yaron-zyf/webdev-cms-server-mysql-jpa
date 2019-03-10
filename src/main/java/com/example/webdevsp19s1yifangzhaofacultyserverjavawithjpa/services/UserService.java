package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.services;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.User;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		List<User> users = (List<User>) userRepository.findAll();
		for (User u : users) {
			if (u.getUserName().equals(user.getUserName())) {
				return null;
			} 
		}
		session.setAttribute("currentUser", user);
		return userRepository.save(user);
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		return currentUser;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session) {
		List<User> users = (List<User>) userRepository.findAll();
		for (User u : users) {
			if (u.getUserName().equals(user.getUserName())
					&& u.getPassword().equals(user.getPassword())) {
				session.setAttribute("currentUser", u);
				return u;
			}
		}
		return null;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("currentUser");
	}
	
	@GetMapping("/api/users")
	public List<User> findAllUsers() {
		return (List<User>)userRepository.findAll();
	}
	
	@GetMapping("/api/user/{id}")
	public User findUserById(@PathVariable int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/api/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		if (userRepository.existsById(id)) {
			return userRepository.save(user);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/api/user/{id}")
	public String deleteUser(@PathVariable int id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "Successfully deleted! " + userRepository.count() + " user(s) left.";
		} else {
			return "User Not Found!";
		}
	}
	
}
