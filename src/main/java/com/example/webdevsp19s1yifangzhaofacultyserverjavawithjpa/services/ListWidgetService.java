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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.ListWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ListWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ListWidgetService {
	
	@Autowired
	TopicService topicService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ListWidgetRepository listWidgetRepository;

	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/list/widget")
	public ListWidget createListWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody ListWidget lw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			lw.setTopic(currentTopic);
			return listWidgetRepository.save(lw);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/list/widgets")
	public List<Widget> findAllListWidgets(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return widgetRepository.findAllWidgetsByType("LIST", tid);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/list/widget/{wid}")
	public ListWidget findListWidgetById(
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
			return listWidgetRepository.findById(wid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/list/widget/{wid}")
	public ListWidget updateListWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid,
			@RequestBody ListWidget lw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !listWidgetRepository.existsById(wid)) {
			return null;
		} else {
			lw.setTopic(currentTopic);
			return listWidgetRepository.save(lw);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/list/widget/{wid}")
	public String deleteListWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !listWidgetRepository.existsById(wid)) {
			return "ListWidget Not Found!";
		} else {
			listWidgetRepository.deleteById(wid);
			return "Successsfully deleted!";
		}
	}
	
}
