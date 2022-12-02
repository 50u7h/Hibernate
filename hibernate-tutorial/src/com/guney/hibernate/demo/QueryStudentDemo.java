package com.guney.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.guney.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display students
			displayStudents(theStudents);

			// query students: lastName='guney'
			theStudents = session.createQuery("from Student s where s.lastName='Güney'").getResultList();

			// display the students
			System.out.println("\n\nStudents who have last name of Güney");
			displayStudents(theStudents);

			// query students: lastName='Güney' or firstName='Samet'
			theStudents = session.createQuery("from Student s where s.lastName = '2' or firstName = 'Samet'")
					.getResultList();

			System.out.println("\n\nStudents who have last name of 2 OR first name Samet");
			displayStudents(theStudents);

			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where" + " s.email LIKE '%guney.com'").getResultList();

			System.out.println("\n\nStudents whose email ends with guney.com");
			displayStudents(theStudents);

			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
