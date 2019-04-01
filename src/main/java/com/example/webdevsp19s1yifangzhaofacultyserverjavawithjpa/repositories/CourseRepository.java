package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Courses course SET course.title = ?1 "
				 + "WHERE course.id = ?2", 
		   nativeQuery = true)
	public void updateCourse(String title, int id);
}
