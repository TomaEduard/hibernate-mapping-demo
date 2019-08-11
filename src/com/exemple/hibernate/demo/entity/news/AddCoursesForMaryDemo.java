package com.exemple.hibernate.demo.entity.news;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exemple.hibernate.demo.entity.Course;
import com.exemple.hibernate.demo.entity.Instructor;
import com.exemple.hibernate.demo.entity.InstructorDetail;
import com.exemple.hibernate.demo.entity.Review;
import com.exemple.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			int studentId = 4;
			Student tempStudent = session.get(Student.class, studentId);
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("\nCourses: " + tempStudent.getCourses());
			
			// add student to courses
			Course tempCourse1 = new Course("Spring Boot Developer");
			Course tempCourse2 = new Course("Google Cloud");
			Course tempCourse3 = new Course("Amazon S3 & BeamStalk");
//			Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
//			Course tempCourse2 = new Course("Atari 2600 - Game Developmnet");
 			tempCourse1.addStudent(tempStudent);
 			tempCourse2.addStudent(tempStudent);
 			
 			// save the courses
 			System.out.println("\nSaving the courses...");
 			session.save(tempCourse1);
 			session.save(tempCourse2);
 			session.save(tempCourse3);
			

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
