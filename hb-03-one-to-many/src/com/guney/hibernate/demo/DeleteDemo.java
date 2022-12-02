package com.guney.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.guney.hibernate.demo.entity.Instructor;
import com.guney.hibernate.demo.entity.InstructorDetail;
import com.guney.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get instructor by primary key/id
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("Found Instructor: " + tempInstructor);

			// delete the instructor
			if (tempInstructor != null) {

				System.out.println("Deleting: " + tempInstructor);

				// Note: will ALSO delete associated "details" object
				// because of Cascade.ALL
				//
				session.delete(tempInstructor);
			}

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			System.out.println(tempInstructor.toString());
		} finally {
			factory.close();
		}
	}

}
