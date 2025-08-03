package com.aurionpro.controller;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.entities.Course;
import com.aurionpro.model.entities.Subject;
import com.aurionpro.service.CourseService;
import com.aurionpro.service.SubjectService;

public class SubjectController {
	 private SubjectService subjectService;
	    private Scanner sc;
	    private CourseService courseService = new CourseService();  // Add this if not already present

	    public SubjectController() {
	        this.subjectService = new SubjectService();
	        this.sc = new Scanner(System.in);
	    }

	    public void subjectMenu() {
	        while (true) {
	            System.out.println("\n==== Subject Management ====");
	            System.out.println("1. Add Subject");
	            System.out.println("2. Delete Subject");
	            System.out.println("3. Restore Deleted Subject");
	            System.out.println("4. Assign Subject to Course");
	            System.out.println("5. View All Subjects");
	            System.out.println("6. View Deleted Subjects");
	            System.out.println("7. View Subjects by Course");
	            System.out.println("0. Back to Main Menu");
	            System.out.print("Enter choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    addSubject();
	                    break;
	                case 2:
	                    deleteSubject();
	                    break;
	                case 3:
	                    restoreSubject();
	                    break;
	                case 4:
	                    assignSubjectToCourseByName();
	                    break;
	                case 5:
	                    viewAllSubjects();
	                    break;
	                case 6:
	                    viewDeletedSubjects();
	                    break;
	                case 7:
	                	displaySubjectsGroupedByCourse();
	                    break;
	                case 0:
	                    return;
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	    }

	    private void addSubject() {
	    
	    	    sc.nextLine(); // clear newline
	    	    System.out.print("Enter subject name: ");
	    	    String name = sc.nextLine();

	    	    List<Course> courses = courseService.getAllCourses();  // Get all courses to display

	    	    if (courses.isEmpty()) {
	    	        System.out.println("❌ No courses found. Please add a course first.");
	    	        return;
	    	    }

	    	    System.out.println("📚 Available Courses:");
	    	    System.out.printf("%-10s %-20s\n", "Course ID", "Course Name");
	    	    for (Course c : courses) {
	    	        System.out.printf("%-10d %-20s\n", c.getCourseId(), c.getName());
	    	    }

	    	    System.out.print("Enter course ID to assign the subject to: ");
	    	    int courseId = sc.nextInt();

	    	    Subject subject = new Subject();
	    	    subject.setName(name);
	    	    subject.setCourseId(courseId);

	    	    if (subjectService.addSubject(subject))
	    	        System.out.println("✅ Subject added successfully.");
	    	    else
	    	        System.out.println("❌ Failed to add subject.");
	    	}


	    private void deleteSubject() {
	        viewAllSubjects();  // show subjects before asking to delete

	        System.out.print("Enter subject ID to delete: ");
	        int id = sc.nextInt();
	        if (subjectService.deleteSubject(id))
	            System.out.println("Subject deleted (soft) successfully.");
	        else
	            System.out.println("Failed to delete subject.");
	    }


	    private void restoreSubject() {
	        List<Subject> deletedSubjects = subjectService.getAllDeletedSubjects();
	        if (deletedSubjects.isEmpty()) {
	            System.out.println("No deleted subjects found.");
	            return;
	        }

	        System.out.println("Deleted Subjects:");
	        System.out.printf("%-10s %-20s %-10s\n", "ID", "Name", "CourseID");
	        for (Subject s : deletedSubjects) {
	            System.out.printf("%-10d %-20s %-10d\n", s.getSubjectId(), s.getName(), s.getCourseId());
	        }

	        System.out.print("Enter subject ID to restore: ");
	        int id = sc.nextInt();
	        if (subjectService.restoreSubject(id))
	            System.out.println("Subject restored successfully.");
	        else
	            System.out.println("Restore failed.");
	    }

	    private void assignSubjectToCourseByName() {
	    	List<Subject> subjects = subjectService.getAllSubjects(); // you must have this method
	    	if (subjects.isEmpty()) {
	    	    System.out.println("❌ No subjects available.");
	    	    return;
	    	}

	    	System.out.println("📘 Available Subjects:");
	    	System.out.printf("%-10s %-20s %-15s\n", "Subject ID", "Subject Name", "Course ID");
	    	for (Subject s : subjects) {
	    	    System.out.printf("%-10d %-20s %-15d\n",
	    	            s.getSubjectId(), s.getName(), s.getCourseId());
	    	}
	    	System.out.print("Enter Subject ID: ");
	        int subjectId = sc.nextInt();
	        sc.nextLine();  // Clear the newline

	        List<Course> courses = courseService.getAllCourses();  // Get all courses to display

    	    if (courses.isEmpty()) {
    	        System.out.println("❌ No courses found. Please add a course first.");
    	        return;
    	    }

    	    System.out.println("📚 Available Courses:");
    	    System.out.printf("%-10s %-20s\n", "Course ID", "Course Name");
    	    for (Course c : courses) {
    	        System.out.printf("%-10d %-20s\n", c.getCourseId(), c.getName());
    	    }
	        System.out.print("Enter Course ID to assign: ");
	        int courseId = sc.nextInt();

	        if (subjectService.assignSubjectToCourseByName(subjectId, courseId)) {
	            System.out.println("✅ Subject assigned to course successfully.");
	        } else {
	            System.out.println("❌ Failed to assign subject. Make sure course name is valid.");
	        }
	    }

	    private void viewAllSubjects() {
	        List<Subject> subjects = subjectService.getAllSubjects();
	        if (subjects.isEmpty()) {
	            System.out.println("No subjects found.");
	            return;
	        }

	        System.out.println("Active Subjects:");
	        System.out.printf("%-10s %-20s %-10s\n", "ID", "Name", "CourseID");
	        for (Subject s : subjects) {
	            System.out.printf("%-10d %-20s %-10d\n", s.getSubjectId(), s.getName(), s.getCourseId());
	        }
	    }

	    private void viewDeletedSubjects() {
	        List<Subject> subjects = subjectService.getAllDeletedSubjects();
	        if (subjects.isEmpty()) {
	            System.out.println("No deleted subjects.");
	            return;
	        }

	        System.out.println("Deleted Subjects:");
	        System.out.printf("%-10s %-20s %-10s\n", "ID", "Name", "CourseID");
	        for (Subject s : subjects) {
	            System.out.printf("%-10d %-20s %-10d\n", s.getSubjectId(), s.getName(), s.getCourseId());
	        }
	    }
	    

	    private void displaySubjectsGroupedByCourse() {
	        List<Subject> subjects = subjectService.getAllActiveSubjectsWithCourseNames();

	        if (subjects.isEmpty()) {
	            System.out.println("❌ No active subjects found.");
	            return;
	        }

	        System.out.println("📘 Subjects Grouped by Course:\n");

	        String currentCourse = "";
	        for (Subject s : subjects) {
	            if (!s.getCourseName().equals(currentCourse)) {
	                currentCourse = s.getCourseName();
	                System.out.println("📚 Course: " + currentCourse);
	                System.out.printf("%-10s %-20s\n", "Subject ID", "Subject Name");
	            }

	            System.out.printf("%-10d %-20s\n", s.getSubjectId(), s.getName());
	        }
	    }

}
