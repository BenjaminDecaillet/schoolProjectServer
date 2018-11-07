package hel.haagahelia.report.school.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Benjamin Décaillet
 *  01/11/2018
 *
 */
@Entity
@Table(name = "grade")
public class Grade {

	/**
	 * Id automatically generated of a grade
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	/**
	 * Name of the grade
	 */
	private String name;
	/**
	 * Value of the grade
	 */
	private int value;
	/**
	 * Weight of the grade
	 */
	private int weight;

	/**
	 * Subject of the grade
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="subjectid")
	private Subject subject;
	/**
	 * Empty constructor
	 */
	public Grade() {}

	/**
	 * Constructor of grade without subject link
	 * @param name
	 * @param value
	 * @param weight
	 */
	public Grade(String name, int value, int weight) {
		super();
		this.name = name;
		this.value = value;
		this.weight = weight;
	}

	/**
	 * Constructor of grade with link to subject
	 * @param name
	 * @param value
	 * @param weight
	 * @param subject
	 */
	public Grade(String name, int value, int weight, Subject subject) {
		super();
		this.name = name;
		this.value = value;
		this.weight = weight;
		this.subject = subject;
	}

	/**
	 * Get the Id of a grade
	 * @return long
	 */
	public long getId() {
		return id;
	}
	/**
	 * Set the id of a grade
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Get the name of a grade
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of a grade
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Get the value of a grade
	 * @return int
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Set the value of a grade
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * Get the weight of a grade
	 * @return int
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * Set the weight of a grade
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	/**
	 * Get the subject of a grade
	 * @return
	 */
	public Subject getSubject() {
		return subject;
	}
	/**
	 * Set the subject of a Grade
	 * @param subject
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}


}
