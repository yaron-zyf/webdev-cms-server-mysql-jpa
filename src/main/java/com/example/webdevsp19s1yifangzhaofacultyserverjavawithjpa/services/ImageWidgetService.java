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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.ImageWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ImageWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ImageWidgetService {
	
	@Autowired
	TopicService topicService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ImageWidgetRepository imageWidgetRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/image/widget")
	public ImageWidget createImageWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody ImageWidget iw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			iw.setTopic(currentTopic);
			return imageWidgetRepository.save(iw);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/image/widgets")
	public List<Widget> findAllImageWidgets(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return widgetRepository.findAllWidgetsByType("IMAGE", tid);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/image/widget/{wid}")
	public ImageWidget findImageWidgetById(
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
			return imageWidgetRepository.findById(wid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/image/widget/{wid}")
	public ImageWidget updateImageWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid,
			@RequestBody ImageWidget iw) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !imageWidgetRepository.existsById(wid)) {
			return null;
		} else {
			iw.setTopic(currentTopic);
			return imageWidgetRepository.save(iw);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/image/widget/{wid}")
	public String deleteImageWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !imageWidgetRepository.existsById(wid)) {
			return "ImageWidget Not Found!";
		} else {
			imageWidgetRepository.deleteById(wid);
			return "Successsfully deleted!";
		}
	}
	
}
