package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Users user SET user.phone = ?1, "
				 + "user.email = ?2 WHERE user.id = ?3", 
		   nativeQuery = true)
	public void updateUser(String phone, String email, int id);
	
}
