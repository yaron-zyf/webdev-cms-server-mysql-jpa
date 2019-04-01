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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Module;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class LessonService {

	@Autowired
	ModuleService moduleService;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@RequestBody Lesson lesson) {
		Module currentModule = moduleService.findModuleById(uid, cid, mid);
		if (currentModule == null) {
			return null;
		} else {
			lesson.setModule(currentModule);
			return lessonRepository.save(lesson);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lessons")
	public List<Lesson> findAllLessons(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid) {
		Module currentModule = moduleService.findModuleById(uid, cid, mid);
		if (currentModule == null) {
			return null;
		} else {
			return currentModule.getLessons();
		}
	}
	
	@GetMapping("/api/lessons")
	public List<Lesson> findAllLessons() {
		List<Module> modules = moduleService.findAllModules();
		List<Lesson> lessons = new ArrayList<Lesson>();
		if (!modules.isEmpty()) {
			for (Module module : modules) {
				List<Lesson> subLessons = module.getLessons();
				if (!subLessons.isEmpty()) {
					lessons.addAll(subLessons);
				}
			}
		}
		return lessons;
	}
	
	@GetMapping("/api/module/{mid}/lessons")
	public List<Lesson> findLessonsForModule(@PathVariable int mid) {
		Module module = moduleService.findModuleById(mid);
		if (module != null) {
			return module.getLessons();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}")
	public Lesson findLessonById(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid) {
		Module currentModule = moduleService.findModuleById(uid, cid, mid);
		if (currentModule == null) {
			return null;
		} else {
			return lessonRepository.findById(lid).orElse(null);
		}
	}
	
	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable int lid) {
		List<Lesson> lessons = findAllLessons();
		if (!lessons.isEmpty()) {
			for (Lesson lesson : lessons) {
				if (lesson.getId() == lid) {
					return lesson;
				}
			}
		}
		return null;
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}")
	public Lesson updateLesson(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid,
			@RequestBody Lesson lesson) {
		Module currentModule = moduleService.findModuleById(uid, cid, mid);
		if (currentModule == null || !lessonRepository.existsById(lid)) {
			return null;
		} else {
			lessonRepository.updateLesson(lesson.getTitle(), lid);
			return lessonRepository.findById(lid).orElse(null);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}/lesson/{lid}")
	public String deleteLesson(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@PathVariable int lid) {
		Module currentModule = moduleService.findModuleById(uid, cid, mid);
		if (currentModule == null || !lessonRepository.existsById(lid)) {
			return "Lesson Not Found!";
		} else {
			lessonRepository.deleteById(lid);
			return "Successsfully deleted!";
		}
	}
	
}
