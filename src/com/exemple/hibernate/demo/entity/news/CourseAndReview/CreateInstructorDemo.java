package com.exemple.hibernate.demo.entity.news.CourseAndReview;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exemple.hibernate.demo.entity.Course;
import com.exemple.hibernate.demo.entity.Instructor;
import com.exemple.hibernate.demo.entity.InstructorDetail;
import com.exemple.hibernate.demo.entity.Review;
import com.exemple.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

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

			// create the object

			Instructor instructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

			InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");

			// associate the object

			instructor.setInstructorDetail(instructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);

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
