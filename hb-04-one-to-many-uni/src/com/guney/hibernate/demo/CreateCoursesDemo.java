package com.guney.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.guney.hibernate.demo.entity.Course;
import com.guney.hibernate.demo.entity.Instructor;
import com.guney.hibernate.demo.entity.InstructorDetail;
import com.guney.hibernate.demo.entity.Review;
import com.guney.hibernate.demo.entity.Student;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			// create some courses
			Course tempCourse1 = new Course("Trigonometri");
			Course tempCourse2 = new Course("Integral");
			Course tempCourse3 = new Course("Olasilik");

			// add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			tempInstructor.add(tempCourse3);

			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);

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
