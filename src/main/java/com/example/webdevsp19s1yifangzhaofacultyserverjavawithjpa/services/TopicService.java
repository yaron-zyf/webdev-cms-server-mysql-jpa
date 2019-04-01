package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Lesson;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class TopicService {

	@Autowired
	LessonService lessonService;
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createTopic(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@RequestBody Topic topic) {
		Lesson currentLesson = lessonService.findLessonById(uid, cid, mid, lid);
		if (currentLesson == null) {
			return null;
		} else {
			topic.setLesson(currentLesson);
			return topicRepository.save(topic);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topics")
	public List<Topic> findAllTopics(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid) {
		Lesson currentLesson = lessonService.findLessonById(uid, cid, mid, lid);
		if (currentLesson == null) {
			return null;
		} else {
			return currentLesson.getTopics();
		}
	}
	
	@GetMapping("/api/topics")
	public List<Topic> findAllTopics() {
		List<Lesson> lessons = lessonService.findAllLessons();
		List<Topic> topics = new ArrayList<Topic>();
		if (!lessons.isEmpty()) {
			for (Lesson lesson : lessons) {
				List<Topic> subTopics = lesson.getTopics();
				if (!subTopics.isEmpty()) {
					topics.addAll(subTopics);
				}
			}
		}
		return topics;
	}
	
	@GetMapping("/api/lesson/{lid}/topics")
	public List<Topic> findTopicsForLesson(@PathVariable int lid) {
		Lesson lesson = lessonService.findLessonById(lid);
		if (lesson != null) {
			return lesson.getTopics();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}")
	public Topic findTopicById(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Lesson currentLesson = lessonService.findLessonById(uid, cid, mid, lid);
		if (currentLesson == null) {
			return null;
		} else {
			return topicRepository.findById(tid).orElse(null);
		}
	}
	
	@GetMapping("/api/topic/{tid}")
	public Topic findTopicById(@PathVariable int tid) {
		List<Topic> topics = findAllTopics();
		if (!topics.isEmpty()) {
			for (Topic topic : topics) {
				if (topic.getId() == tid) {
					return topic;
				}
			}
		}
		return null;
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}")
	public Topic updateTopic(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody Topic topic) {
		Lesson currentLesson = lessonService.findLessonById(uid, cid, mid, lid);
		if (currentLesson == null || !topicRepository.existsById(tid)) {
			return null;
		} else {
			topicRepository.updateTopic(topic.getTitle(), tid);
			return topicRepository.findById(tid).orElse(null);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}")
	public String deleteTopic(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Lesson currentLesson = lessonService.findLessonById(uid, cid, mid, lid);
		if (currentLesson == null || !topicRepository.existsById(tid)) {
			return "Topic Not Found!";
		} else {
			topicRepository.deleteById(tid);
			return "Successsfully deleted!";
		}
	}
	
}
