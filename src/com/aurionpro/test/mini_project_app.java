package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.controller.CourseController;
import com.aurionpro.controller.SubjectController;

public class mini_project_app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
	        CourseController courseController = new CourseController();
	        SubjectController subjectController = new SubjectController();

	        while (true) {
	            System.out.println("\n===== Main Menu =====");
	            System.out.println("1. Course Management");
	            System.out.println("2. Subject Management");
	            System.out.println("0. Exit");
	            System.out.print("Enter choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    courseController.courseMenu();
	                    break;
	                case 2:
	                    subjectController.subjectMenu();
	                    break;
	                case 0:
	                    System.out.println("Exiting program.");
	                    sc.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	}

}
