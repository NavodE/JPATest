package se.navod.platform.test.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "student")
@Audited
public class Student implements Serializable {

    private static final long serialVersionUID = -834531085311709309L;

    @Column(name = "student_id", unique = true)
    private int studentId;

    @Id
    @Column(name = "primaryKey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "student_age", nullable = false)
    private int age;

    @Version
    private long version;

    public Student() {
	super();
    }

    public Student(int studentId, String name, int age) {
	super();
	this.studentId = studentId;
	this.name = name;
	this.age = age;
    }

    public void setId(int id) {
	this.id = id;
    }

    public long getVersion() {
	return version;
    }

    public void setVersion(long version) {
	this.version = version;
    }

    public int getStudentId() {
	return studentId;
    }

    public void setStudentId(int studentId) {
	this.studentId = studentId;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    @Override
    public String toString() {
	return id + "\t" + name + "\t" + age;
    }
}
