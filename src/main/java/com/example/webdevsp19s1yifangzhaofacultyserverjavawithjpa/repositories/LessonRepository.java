package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Lessons lesson SET lesson.title = ?1 "
				 + "WHERE lesson.id = ?2", 
		   nativeQuery = true)
	public void updateLesson(String title, int id);
}
