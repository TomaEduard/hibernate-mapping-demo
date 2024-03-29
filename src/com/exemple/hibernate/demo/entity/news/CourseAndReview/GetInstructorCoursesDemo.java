package com.exemple.hibernate.demo.entity.news.CourseAndReview;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exemple.hibernate.demo.entity.Course;
import com.exemple.hibernate.demo.entity.Instructor;
import com.exemple.hibernate.demo.entity.InstructorDetail;
import com.exemple.hibernate.demo.entity.Review;
import com.exemple.hibernate.demo.entity.Student;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);
			
			System.out.println("Instructor: " + tempInstructor);
			// get courses for the instructor
			System.out.println("Courses: " + tempInstructor.getCourse());
			
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
