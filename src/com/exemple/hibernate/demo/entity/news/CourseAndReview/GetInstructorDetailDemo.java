 package com.exemple.hibernate.demo.entity.news.CourseAndReview;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exemple.hibernate.demo.entity.Course;
import com.exemple.hibernate.demo.entity.Instructor;
import com.exemple.hibernate.demo.entity.InstructorDetail;
import com.exemple.hibernate.demo.entity.Review;
import com.exemple.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

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

			// get the instructor detail object
			int id = 221;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			// print the instructor detail
			System.out.println("instructorDetail: " + instructorDetail);
			
			// print the associated instuctor
			System.out.println("the associated instructor: " + instructorDetail.getInstructor());
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			
		  	}catch (Exception e) {
				e.printStackTrace();
			}
		 finally {
			 // handle connection leak issue
			 session.close();
			 
			factory.close();
		}
	}

}
