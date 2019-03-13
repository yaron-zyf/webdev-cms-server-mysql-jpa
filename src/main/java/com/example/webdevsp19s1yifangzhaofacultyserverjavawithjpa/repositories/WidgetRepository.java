package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	
	@Query(value = "SELECT * FROM Widgets widget"
					+ " WHERE widget.type = :type"
					+ " AND widget.topic_id = :topicId",
		  nativeQuery = true)
	public List<Widget> findAllWidgetsByType(
			@Param("type") String type,
			@Param("topicId") int topicId);
	
}
