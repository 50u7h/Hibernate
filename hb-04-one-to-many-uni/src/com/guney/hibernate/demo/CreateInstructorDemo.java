package com.guney.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.guney.hibernate.demo.entity.Course;
import com.guney.hibernate.demo.entity.Instructor;
import com.guney.hibernate.demo.entity.InstructorDetail;
import com.guney.hibernate.demo.entity.Review;
import com.guney.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the object
			Instructor tempInstructor = new Instructor("test2", "test2", "2test@test.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.test2.com/test2", "TEST2");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of Cascade.ALL
			//
			System.out.println("Saving the instructor...");
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			System.out.println(tempInstructor.toString());
		} finally {
			// handling connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
