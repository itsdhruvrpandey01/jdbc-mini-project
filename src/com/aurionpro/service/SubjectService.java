package com.aurionpro.service;

import java.util.List;

import com.aurionpro.model.dao.SubjectDao;
import com.aurionpro.model.entities.Subject;

public class SubjectService {
	 private SubjectDao subjectDao;

	    public SubjectService() {
	        this.subjectDao = new SubjectDao();
	    }

	    public boolean addSubject(Subject subject) {
	        return subjectDao.addSubject(subject);
	    }

	    public boolean deleteSubject(int subjectId) {
	        return subjectDao.deleteSubject(subjectId);
	    }

	    public boolean restoreSubject(int subjectId) {
	        return subjectDao.restoreSubject(subjectId);
	    }

	    public List<Subject> getAllSubjects() {
	        return subjectDao.getAllSubjects();
	    }

	    public List<Subject> getAllDeletedSubjects() {
	        return subjectDao.getAllDeletedSubjects();
	    }

	    public boolean assignSubjectToCourseByName(int subjectId, int courseId) {
	        return subjectDao.assignSubjectToCourseByName(subjectId, courseId);
	    }

	    public List<Subject> getAllActiveSubjectsWithCourseNames() {
	        return subjectDao.getAllActiveSubjectsWithCourseNames();
	    }

	   
	    public int getCourseIdByName(String courseName) {
	        return subjectDao.getCourseIdByName(courseName);
	    }
	    public List<Subject> getSubjectsByCourseName(String courseName) {
	        return subjectDao.getSubjectsByCourseName(courseName);
	    }

}
