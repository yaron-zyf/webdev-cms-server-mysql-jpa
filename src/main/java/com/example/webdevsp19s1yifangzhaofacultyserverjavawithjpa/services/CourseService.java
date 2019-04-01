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
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.User;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class CourseService {

	@Autowired
	UserService userService;
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/user/{uid}/course")
	public Course createCourse(@PathVariable int uid, @RequestBody Course course) {
		User currentUser = userService.findUserById(uid);
		if (currentUser == null) {
			return null;
		} else {
			course.setUser(currentUser);
			return courseRepository.save(course);
		}
	}
	
	@GetMapping("/api/user/{uid}/courses")
	public List<Course> findAllCourses(@PathVariable int uid) {
		User currentUser = userService.findUserById(uid);
		if (currentUser == null) {
			return null;
		} else {
			return currentUser.getCourses();
		}
	}
	
	@GetMapping("/api/user/{uid}/course/{cid}")
	public Course findCourseById(@PathVariable int uid, @PathVariable int cid) {
		User currentUser = userService.findUserById(uid);
		if (currentUser == null) {
			return null;
		} else {
			return courseRepository.findById(cid).orElse(null);
		}
	}
	
	@PutMapping("/api/user/{uid}/course/{cid}")
	public Course updateCourse(
			@PathVariable int uid, 
			@PathVariable int cid, 
			@RequestBody Course course) {
		User currentUser = userService.findUserById(uid);
		if (currentUser == null || !courseRepository.existsById(cid)) {
			return null;
		} else {
			courseRepository.updateCourse(course.getTitle(), cid);
			return courseRepository.findById(cid).orElse(null);
		}
	}
	
	@DeleteMapping("/api/user/{uid}/course/{cid}")
	public String deleteCourse(@PathVariable int uid, @PathVariable int cid) {
		User currentUser = userService.findUserById(uid);
		if (currentUser == null || !courseRepository.existsById(cid)) {
			return "Course Not Found!";
		} else {
			courseRepository.deleteById(cid);
			return "Successfully deleted!";
		}
	}
	
}
