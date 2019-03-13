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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.ParagraphWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ParagraphWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ParagraphWidgetService {
	
	@Autowired
	TopicService topicService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ParagraphWidgetRepository paragraphWidgetRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/paragraph/widget")
	public ParagraphWidget createParagraphWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody ParagraphWidget pw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			pw.setTopic(currentTopic);
			return paragraphWidgetRepository.save(pw);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/paragraph/widgets")
	public List<Widget> findAllParagraphWidgets(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return widgetRepository.findAllWidgetsByType("PARAGRAPH", tid);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/paragraph/widget/{wid}")
	public ParagraphWidget findParagraphWidgetById(
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
			return paragraphWidgetRepository.findById(wid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/paragraph/widget/{wid}")
	public ParagraphWidget updateParagraphWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid,
			@RequestBody ParagraphWidget pw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !paragraphWidgetRepository.existsById(wid)) {
			return null;
		} else {
			pw.setTopic(currentTopic);
			return paragraphWidgetRepository.save(pw);
		}
	}

	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/paragraph/widget/{wid}")
	public String deleteParagraphWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !paragraphWidgetRepository.existsById(wid)) {
			return "ParagraphWidget Not Found!";
		} else {
			widgetRepository.deleteById(wid);
			return "Successsfully deleted!";
		}
	}
	
}
