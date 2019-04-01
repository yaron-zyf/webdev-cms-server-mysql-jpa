package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Modules module SET module.title = ?1 "
				 + "WHERE module.id = ?2", 
		   nativeQuery = true)
	public void updateModule(String title, int id);
}
