package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.services;

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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.HeadingWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.HeadingWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class HeadingWidgetService {
	
	@Autowired
	TopicService topicService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	HeadingWidgetRepository headingWidgetRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/heading/widget")
	public HeadingWidget createHeadingWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody HeadingWidget hw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			hw.setTopic(currentTopic);
			return headingWidgetRepository.save(hw);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/heading/widgets")
	public List<Widget> findAllHeadingWidgets(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return widgetRepository.findAllWidgetsByType("HEADING", tid);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/heading/widget/{wid}")
	public HeadingWidget findHeadingWidgetById(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return headingWidgetRepository.findById(wid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/heading/widget/{wid}")
	public HeadingWidget updateHeadingWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid,
			@RequestBody HeadingWidget hw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !headingWidgetRepository.existsById(wid)) {
			return null;
		} else {
			hw.setTopic(currentTopic);
			return headingWidgetRepository.save(hw);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/heading/widget/{wid}")
	public String deleteHeadingWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !headingWidgetRepository.existsById(wid)) {
			return "HeadingWidget Not Found!";
		} else {
			headingWidgetRepository.deleteById(wid);
			return "Successsfully deleted!";
		}
	}
	
}
