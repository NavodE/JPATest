package se.navod.platform.test.hibernate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PlatformTest");

    public static void main(String[] args) {
	EntityTransaction transaction = null;
	try {
	    int id = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
	    String name = "Name_" + id;
	    create(id, name, 10);// Create the student

	    EntityManager manager = entityManagerFactory.createEntityManager();
	    transaction = manager.getTransaction();

	    transaction.begin();// Start the transaction

	    Student student1 = findStudentByPropertyValue(manager, name, "name");// Retrieve the student by name
	    student1.setName("NewName");// Change the name

	    // Retrieve the same student again (by id)
	    Student student2 = findStudentByPropertyValue(manager, "" + id, "studentId");

	    student2.setAge(50);// Change the age
	    manager.persist(student2);// Persist and commit

	    transaction.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    if (transaction != null) {
		transaction.rollback();
	    }
	} finally {
	    if (entityManagerFactory != null) {
		entityManagerFactory.close();
	    }
	}
    }

    public static Student create(int id, String name, int age) {
	EntityManager manager = entityManagerFactory.createEntityManager();
	EntityTransaction transaction = null;

	try {
	    transaction = manager.getTransaction();
	    transaction.begin();
	    Student stu = new Student(id, name, age);
	    manager.persist(stu);
	    transaction.commit();
	    return stu;
	} catch (Exception ex) {
	    if (transaction != null) {
		transaction.rollback();
	    }
	    ex.printStackTrace();
	    throw ex;
	} finally {
	    manager.close();
	}
    }

    private static Student findStudentByPropertyValue(EntityManager manager, String value, String property) {
	CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
	CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
	Root<Student> itemRoot = criteriaQuery.from(Student.class);

	Predicate predicate = criteriaBuilder.equal(itemRoot.get(property), value);
	criteriaQuery.where(predicate);
	criteriaQuery.orderBy(criteriaBuilder.asc(itemRoot.get("id")));

	List<Student> items = manager.createQuery(criteriaQuery).getResultList();
	return items.size() == 0 ? null : items.get(0);
    }

}
