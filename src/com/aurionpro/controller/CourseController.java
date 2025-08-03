package com.aurionpro.controller;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.entities.Course;
import com.aurionpro.service.CourseService;

public class CourseController {
	 private final CourseService courseService;
	    private final Scanner scanner;

	    public CourseController() {
	        courseService = new CourseService();
	        scanner = new Scanner(System.in);
	    }

	    public void courseMenu() {
	        boolean exit = false;
	        while (!exit) {
	            System.out.println("\n--- Course Management ---");
	            System.out.println("1. Add Course");
	            System.out.println("2. Update Course");
	            System.out.println("3. Delete Course");
	            System.out.println("4. View Course by ID");
	            System.out.println("5. View All Courses");
	            System.out.println("6. Restore Deleted Course");
	            System.out.println("7. Exit");


	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1 -> addCourse();
	                case 2 -> updateCourse();
	                case 3 -> deleteCourse();
	                case 4 -> viewCourseById();
	                case 5 -> viewAllCourses();
	                case 6 -> restoreCourse();     
	                case 7 -> exit = true;
	                default -> System.out.println("Invalid choice!");
	            }
	        }
	    }

	    private void addCourse() {
	        System.out.print("Enter Course ID: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        System.out.print("Enter Course Name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter Course Duration (in months): ");
	        int duration = scanner.nextInt();

	        Course course = new Course(id, name, duration);
	        if (courseService.addCourse(course))
	            System.out.println("Course added successfully.");
	        else
	            System.out.println("Failed to add course.");
	    }

	    private void updateCourse() {
	        System.out.print("Enter Course ID: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        System.out.print("Enter New Course Name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter New Duration (in months): ");
	        int duration = scanner.nextInt();

	        Course course = new Course(id, name, duration);
	        if (courseService.updateCourse(course))
	            System.out.println("Course updated.");
	        else
	            System.out.println("Failed to update.");
	    }

	    private void deleteCourse() {
	        System.out.print("Enter Course ID to delete: ");
	        int id = scanner.nextInt();
	        if (courseService.deleteCourse(id))
	            System.out.println("Course deleted .");
	        else
	            System.out.println("Delete failed.");
	    }

	    private void viewCourseById() {
	    	System.out.print("Enter Course ID: ");
	        int id = scanner.nextInt();
	        Course course = courseService.getCourseById(id);

	        if (course != null) {
	            System.out.printf("%-10s %-30s %-20s%n", "Course ID", "Course Name", "Duration (Months)");
	            System.out.println("---------------------------------------------------------------");
	            System.out.printf("%-10d %-30s %-20d%n",
	                    course.getCourseId(),
	                    course.getName(),
	                    course.getDurationInMonths());
	        } else {
	            System.out.println("Course not found.");
	        }
	    }

	    private void viewAllCourses() {
	    	 List<Course> courses = courseService.getAllCourses();
	    	    
	    	    if (courses.isEmpty()) {
	    	        System.out.println("No courses available.");
	    	        return;
	    	    }

	    	    System.out.printf("%-10s %-30s %-20s%n", "Course ID", "Course Name", "Duration (Months)");
	    	    System.out.println("---------------------------------------------------------------");
	    	    
	    	    for (Course course : courses) {
	    	        System.out.printf("%-10d %-30s %-20d%n",
	    	                course.getCourseId(),
	    	                course.getName(),
	    	                course.getDurationInMonths());
	    	    }
	    }
	    private void restoreCourse() {
	    	 List<Course> deletedCourses = courseService.getAllDeletedCourses();

	    	    if (deletedCourses.isEmpty()) {
	    	        System.out.println("No deleted courses to restore.");
	    	        return;
	    	    }

	    	    System.out.println("\n--- Deleted Courses ---");
	    	    System.out.printf("%-10s %-30s %-20s%n", "ID", "Course Name", "Duration (Months)");
	    	    System.out.println("--------------------------------------------------------------");

	    	    for (Course course : deletedCourses) {
	    	        System.out.printf("%-10d %-30s %-20d%n", course.getCourseId(), course.getName(), course.getDurationInMonths());
	    	    }
	        System.out.print("Enter Course ID to restore: ");
	        int id = scanner.nextInt();
	        boolean success = courseService.restoreCourse(id);
	        if (success) {
	            System.out.println("Course restored successfully.");
	        } else {
	            System.out.println("Course not found or already active.");
	        }
	    }
}
