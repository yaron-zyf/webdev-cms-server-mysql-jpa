package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Topics topic SET topic.title = ?1 "
				 + "WHERE topic.id = ?2", 
		   nativeQuery = true)
	public void updateTopic(String title, int id);
}
