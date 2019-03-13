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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.LinkWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.LinkWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class LinkWidgetService {

	@Autowired
	TopicService topicService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LinkWidgetRepository linkWidgetRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/link/widget")
	public LinkWidget createLinkWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody LinkWidget lw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			lw.setTopic(currentTopic);
			return linkWidgetRepository.save(lw);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/link/widgets")
	public List<Widget> findAllLinkWidgets(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return widgetRepository.findAllWidgetsByType("LINK", tid);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/link/widget/{wid}")
	public LinkWidget findLinkWidgetById(
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
			return linkWidgetRepository.findById(wid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/link/widget/{wid}")
	public LinkWidget updateLinkWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid,
			@RequestBody LinkWidget lw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !linkWidgetRepository.existsById(wid)) {
			return null;
		} else {
			lw.setTopic(currentTopic);
			return linkWidgetRepository.save(lw);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/link/widget/{wid}")
	public String deleteLinkWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !linkWidgetRepository.existsById(wid)) {
			return "LinkWidget Not Found!";
		} else {
			linkWidgetRepository.deleteById(wid);
			return "Successsfully deleted!";
		}
	}
	
}
