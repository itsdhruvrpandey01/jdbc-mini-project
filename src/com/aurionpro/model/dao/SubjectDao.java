package com.aurionpro.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.connector.Connector;
import com.aurionpro.model.entities.Subject;

public class SubjectDao {
	private Connection connection;

    public SubjectDao() {
        Connector connector = new Connector();
        this.connection = connector.getConnection();
    }

    public boolean addSubject(Subject subject) {
        String query = "INSERT INTO subject (subject_name, course_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getCourseId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Add Subject Failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteSubject(int subjectId) {
    	getAllSubjects();
        String query = "UPDATE subject SET is_active = false WHERE subject_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Subject Failed: " + e.getMessage());
            return false;
        }
    }

    public boolean restoreSubject(int subjectId) {
        String query = "UPDATE subject SET is_active = true WHERE subject_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Restore Subject Failed: " + e.getMessage());
            return false;
        }
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subject WHERE is_active = true ORDER BY subject_id";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setName(rs.getString("subject_name"));
                subject.setCourseId(rs.getInt("course_id"));
                subject.setActive(rs.getBoolean("is_active"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            System.err.println("Get All Subjects Failed: " + e.getMessage());
        }
        return subjects;
    }

    public List<Subject> getAllDeletedSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subject WHERE is_active = false ORDER BY subject_id";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setName(rs.getString("subject_name"));
                subject.setCourseId(rs.getInt("course_id"));
                subject.setActive(rs.getBoolean("is_active"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            System.err.println("Get Deleted Subjects Failed: " + e.getMessage());
        }
        return subjects;
    }

    public Subject getSubjectById(int subjectId) {
        String query = "SELECT * FROM subject WHERE subject_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setName(rs.getString("subject_name"));
                    subject.setCourseId(rs.getInt("course_id"));
                    subject.setActive(rs.getBoolean("is_active"));
                    return subject;
                }
            }
        } catch (SQLException e) {
            System.err.println("Get Subject By ID Failed: " + e.getMessage());
        }
        return null;
    }
    public boolean assignSubjectToCourseByName(int subjectId, int courseId) {
        String query = "UPDATE subject SET course_id = ? WHERE subject_id = ? AND is_active = true";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);  
            stmt.setInt(2, subjectId);  
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Assign Subject to Course Failed: " + e.getMessage());
            return false;
        }
    }
    
        
    public int getCourseIdByName(String courseName) {
        String query = "SELECT course_id FROM course WHERE course_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("course_id");
            }
        } catch (SQLException e) {
            System.err.println("Get Course ID by Name Failed: " + e.getMessage());
        }
        return -1; 
    }
   
    public List<Subject> getSubjectsByCourseName(String courseName) {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subject " +
                       "WHERE course_id = (SELECT course_id FROM course WHERE coursename = ?) " +
                       "AND is_active = true";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, courseName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setName(rs.getString("subject_name"));
                    subject.setCourseId(rs.getInt("course_id"));
                    subject.setActive(rs.getBoolean("is_active"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            System.err.println("Get Subjects by Course Name Failed: " + e.getMessage());
        }
        return subjects;
    }

    public List<Subject> getAllActiveSubjectsWithCourseNames() {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT s.subject_id, s.subject_name, s.course_id, s.is_active, c.coursename " +
                       "FROM subject s JOIN course c ON s.course_id = c.courseid " +
                       "WHERE s.is_active = true ORDER BY c.coursename, s.subject_name";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setName(rs.getString("subject_name"));
                    subject.setCourseId(rs.getInt("course_id"));
                    subject.setActive(rs.getBoolean("is_active"));
                    subject.setCourseName(rs.getString("coursename")); 
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            System.err.println("Fetching subjects with course names failed: " + e.getMessage());
        }
        return subjects;
    }

}
