package com.exemple.hibernate.demo.entity.news;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exemple.hibernate.demo.entity.Course;
import com.exemple.hibernate.demo.entity.Instructor;
import com.exemple.hibernate.demo.entity.InstructorDetail;
import com.exemple.hibernate.demo.entity.Review;
import com.exemple.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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

			// create a course
			Course tempCourse = new Course("Pacman - How To Score On Million Points");
			
			// save the course
			System.out.println("\n Saving the course ...");
			session.save(tempCourse);
			System.out.println("Save the course: " + tempCourse);
			
			// create the student
			Student tempStudent1 = new Student("Jhon", "Doe", "john@yahoo.com");
			Student tempStudent2 = new Student("Marry", "Public", "marry@gmail.com");
		
			// add student to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSave students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved student: " + tempCourse.getStudents());
			
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