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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Course;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.Module;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ModuleService {

	@Autowired
	CourseService courseService;
	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/user/{uid}/course/{cid}/module")
	public Module createModule(
			@PathVariable int uid,
			@PathVariable int cid,
			@RequestBody Module module) {
		Course currentCourse = courseService.findCourseById(uid, cid);
		if (currentCourse == null) {
			return null;
		} else {
			module.setCourse(currentCourse);
			return moduleRepository.save(module);
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/modules")
	public List<Module> findAllModules(
			@PathVariable int uid, 
			@PathVariable int cid) {
		Course currentCourse = courseService.findCourseById(uid, cid);
		if (currentCourse == null) {
			return null;
		} else {
			return currentCourse.getModules();
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}/module/{mid}")
	public Module findModuleById(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid) {
		Course currentCourse = courseService.findCourseById(uid, cid);
		if (currentCourse == null) {
			return null;
		} else {
			return moduleRepository.findById(mid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}/module/{mid}")
	public Module updateModule(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid,
			@RequestBody Module module) {
		Course currentCourse = courseService.findCourseById(uid, cid);
		if (currentCourse == null || !moduleRepository.existsById(mid)) {
			return null;
		} else {
			moduleRepository.updateModule(module.getTitle(), mid);
			return moduleRepository.findById(mid).orElse(null);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}/module/{mid}")
	public String deleteModule(
			@PathVariable int uid,
			@PathVariable int cid,
			@PathVariable int mid) {
		Course currentCourse = courseService.findCourseById(uid, cid);
		if (currentCourse == null || !moduleRepository.existsById(mid)) {
			return "Module Not Found!";
		} else {
			moduleRepository.deleteById(mid);
			return "Successsfully deleted!";
		}
	}
	
}
