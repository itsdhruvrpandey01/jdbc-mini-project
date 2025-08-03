package com.aurionpro.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.connector.Connector;
import com.aurionpro.model.entities.Course;

public class CourseDao {
	private Connection connection;

    public CourseDao() {
        Connector connector = new Connector();
        this.connection = connector.getConnection();
    }

    public boolean addCourse(Course course) {
        String query = "INSERT INTO course (coursename, duration_in_months) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getDurationInMonths());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Add Course Failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCourse(Course course) {
        String query = "UPDATE course SET coursename = ?, duration_in_months = ? WHERE courseid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getDurationInMonths());
            stmt.setInt(3, course.getCourseId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Course Failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCourse(int courseId) {
        String query = "UPDATE course SET is_active = false WHERE courseid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Course Failed: " + e.getMessage());
        }
        return false;
    }

    public Course getCourseById(int courseId) {
        String query = "SELECT * FROM course WHERE courseid = ? AND is_active = true";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                        rs.getInt("courseid"),
                        rs.getString("coursename"),
                        rs.getInt("duration_in_months")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Get Course By ID Failed: " + e.getMessage());
        }
        return null;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM course WHERE is_active = true order by courseid";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("courseid"),
                    rs.getString("coursename"),
                    rs.getInt("duration_in_months")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            System.err.println("Fetch All Courses Failed: " + e.getMessage());
        }
        return courses;
    }
    public boolean restoreCourse(int courseId) {
        String query = "UPDATE course SET is_active = true WHERE courseid = ? AND is_active = false";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Restore Failed: " + e.getMessage());
            return false;
        }
    }
    public List<Course> getAllDeletedCourses() {
        List<Course> deletedCourses = new ArrayList<>();
        String query = "SELECT * FROM course WHERE is_active = false";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("courseid"),
                    rs.getString("coursename"),
                    rs.getInt("duration_in_months")
                );
                deletedCourses.add(course);
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch deleted courses: " + e.getMessage());
        }
        return deletedCourses;
    }
    
}
