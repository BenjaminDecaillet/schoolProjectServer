package hel.haagahelia.report.school.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private double average;
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="subject")
	private List<Grade> grades;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="studentid")
	private Student student;
	/**
	 * Empty constructor
	 */
	public Subject() {}

	/**
	 * Constructor
	 * @param name
	 */
	public Subject(String name) {
		super();
		this.name = name;
		this.average = 0;
		this.grades = new ArrayList<Grade>();
	}
	/**
	 * Constructor
	 * @param name
	 * @param student
	 */
	public Subject(String name,Student student) {
		super();
		this.name = name;
		this.average = 0;
		this.student= student;
		this.grades = new ArrayList<Grade>();
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the grades
	 */
	public List<Grade> getGrades() {
		return grades;
	}

	/**
	 * @param grades the grades to set
	 */
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
		
}
