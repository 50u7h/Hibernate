package com.guney.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.guney.hibernate.demo.entity.Course;
import com.guney.hibernate.demo.entity.Instructor;
import com.guney.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("guney: Instructor: " + tempInstructor);
			
			System.out.println("guney: Courses: " + tempInstructor.getCourses());

			// commit transaction
			session.getTransaction().commit();
			
			// close session
			session.close();
			
			System.out.println("\nguney: The session is now closed\n");
			
			// option 1: call getter method while session is open
			
			// this should fail
			// get courses for the instructor
			System.out.println("guney: Courses: " + tempInstructor.getCourses());

			System.out.println("guney: Done!");
		} finally {
			// handling connection leak issue
			session.close();

			factory.close();
		}
	}

}
