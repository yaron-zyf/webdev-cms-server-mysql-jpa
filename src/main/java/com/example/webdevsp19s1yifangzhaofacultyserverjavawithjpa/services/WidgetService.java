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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.ImageWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.LinkWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.ListWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.ParagraphWidget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Topic;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Widget;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.HeadingWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ImageWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.LinkWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ListWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ParagraphWidgetRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class WidgetService {

	@Autowired
	TopicService topicService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	HeadingWidgetRepository headingWidgetRepository;
	@Autowired
	ParagraphWidgetRepository paragraphWidgetRepository;
	@Autowired
	ListWidgetRepository listWidgetRepository;
	@Autowired
	ImageWidgetRepository imageWidgetRepository;
	@Autowired
	LinkWidgetRepository linkWidgetRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/widget")
	public Widget createWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@RequestBody HeadingWidget widget) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			widget.setTopic(currentTopic);
			return headingWidgetRepository.save(widget);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/widgets")
	public List<Widget> findAllWidgets(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null) {
			return null;
		} else {
			return currentTopic.getWidgets();
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/widget/{wid}")
	public Widget findWidgetById(
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
			return widgetRepository.findById(wid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/widget/{wid}")
	public Widget updateWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid,
			@RequestBody Widget widget) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !widgetRepository.existsById(wid)) {
			return null;
		} else {
			widgetRepository.deleteById(wid);
			String type = widget.getType();
			if (type.equals("HEADING")) {
				HeadingWidget hw = new HeadingWidget(wid, type);
				hw.setHeadingSize(1);
				hw.setHeadingText("Heading text");
				hw.setTopic(currentTopic);
				return headingWidgetRepository.save(hw);
			} else if (type.equals("PARAGRAPH")){
				ParagraphWidget pw = new ParagraphWidget(wid, type);
				pw.setParagraphText("Paragraph text");
				pw.setTopic(currentTopic);
				return paragraphWidgetRepository.save(pw);
			} else if (type.equals("LIST")) {
				ListWidget lw = new ListWidget(wid, type);
				lw.setListStyle("unordered");
				lw.setListItems("Enter one list item per line");
				lw.setTopic(currentTopic);
				return listWidgetRepository.save(lw);
			} else if (type.equals("IMAGE")) {
				ImageWidget iw = new ImageWidget(wid, type);
				iw.setImageSrc("Image URL");
				iw.setTopic(currentTopic);
				return imageWidgetRepository.save(iw);
			} else if (type.equals("LINK")) {
				LinkWidget lw = new LinkWidget(wid, type);
				lw.setLinkTitle("Link text");
				lw.setLinkHref("Link URL");
				lw.setTopic(currentTopic);
				return linkWidgetRepository.save(lw);
			} else {
				return null;
			}
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}/topic/{tid}/widget/{wid}")
	public String deleteWidget(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@PathVariable int tid,
			@PathVariable int wid) {
		Topic currentTopic = topicService.findTopicById(uid, cid, mid, lid, tid);
		if (currentTopic == null || !widgetRepository.existsById(wid)) {
			return "Widget Not Found!";
		} else {
			widgetRepository.deleteById(wid);
			return "Successsfully deleted!";
		}
	}
	
}
