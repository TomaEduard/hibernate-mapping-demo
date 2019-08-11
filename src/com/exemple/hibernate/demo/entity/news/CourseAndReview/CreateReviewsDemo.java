package com.exemple.hibernate.demo.entity.news.CourseAndReview;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exemple.hibernate.demo.entity.Course;
import com.exemple.hibernate.demo.entity.Instructor;
import com.exemple.hibernate.demo.entity.InstructorDetail;
import com.exemple.hibernate.demo.entity.Review;
import com.exemple.hibernate.demo.entity.Student;

public class CreateReviewsDemo {

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

			// get a course
			int courseId = 19;
			Course tempCourse = session.get(Course.class, courseId);
			
			// add some reviews
			tempCourse.addReview(new Review("Great course ... loved it!"));
			tempCourse.addReview(new Review("Cool course, job well done"));
			tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));
			
			// save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(".getReview(): " + tempCourse.getReview());
			session.save(tempCourse);
			
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
