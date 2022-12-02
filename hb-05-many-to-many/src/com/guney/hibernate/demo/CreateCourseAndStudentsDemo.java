
package com.guney.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.guney.hibernate.demo.entity.Course;
import com.guney.hibernate.demo.entity.Instructor;
import com.guney.hibernate.demo.entity.InstructorDetail;
import com.guney.hibernate.demo.entity.Review;
import com.guney.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Rammstein 101");

			// save the course
			System.out.println("\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved course: " + tempCourse);

			// create the students
			Student tempStudent1 = new Student("Samet", "Guney", "samet@guney.com");
			Student tempStudent2 = new Student("TEST", "test", "TEST@test.com");

			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);

			// save the students
			System.out.println("Saving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: " + tempCourse.getStudents());

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