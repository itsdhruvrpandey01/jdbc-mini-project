package com.aurionpro.service;

import java.util.List;

import com.aurionpro.model.dao.CourseDao;
import com.aurionpro.model.entities.Course;

public class CourseService {
	private final CourseDao courseDao;

    public CourseService() {
        this.courseDao = new CourseDao();
    }

    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    public boolean updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseDao.deleteCourse(courseId);
    }

    public Course getCourseById(int id) {
        return courseDao.getCourseById(id);
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }
    public boolean restoreCourse(int courseId) {
        return courseDao.restoreCourse(courseId);
    }
    public List<Course> getAllDeletedCourses() {
        return courseDao.getAllDeletedCourses();
    }

}
