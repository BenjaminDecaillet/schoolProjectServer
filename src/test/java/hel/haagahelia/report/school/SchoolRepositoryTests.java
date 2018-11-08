package hel.haagahelia.report.school;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import hel.haagahelia.report.school.domain.Grade;
import hel.haagahelia.report.school.domain.GradeRepository;
import hel.haagahelia.report.school.domain.Student;
import hel.haagahelia.report.school.domain.StudentRepository;
import hel.haagahelia.report.school.domain.Subject;
import hel.haagahelia.report.school.domain.SubjectRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DataJpaTest
public class SchoolRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Test
	public void t1_saveStudent() {
		Student student = new Student("test","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","ben@mail.com","ADMIN");
		entityManager.persistAndFlush(student);
		assertThat(student.getId()).isNotNull();
	}
	@Test
	public void t2_saveSubject() {
		Subject subject = new Subject("Business Intelligence");
		entityManager.persistAndFlush(subject);
		assertThat(subject.getId()).isNotNull();
	}
	@Test
	public void t3_saveGrade() {
		Grade grade = new Grade("Mid Term exam",1,1);
		entityManager.persistAndFlush(grade);
		assertThat(grade.getId()).isNotNull();
	}
	@Test
	public void t4_findStudentByUsername() {
		Student john = new Student("john","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","admin@mail.com","USER");
		entityManager.persistAndFlush(john);
		assertThat(studentRepository.findByUsername("john").getId())
		.isEqualTo(john.getId())
		.isNotNull();
	}
	@Test
	public void t5_findSubjectsByStudent() {
		Student student = studentRepository.findByUsername("ben");
		assertThat(subjectRepository.findByStudent(student).get(0)).isNotNull();
	}
	@Test
	public void t6_findGradeBySubject() {
		Student student = studentRepository.findByUsername("ben");
		List<Subject> subjects = subjectRepository.findByStudent(student);
		Subject subject = subjects.get(0);
		assertThat(gradeRepository.findBySubject(subject).get(0)).isNotNull();
	}
	@Test
	public void t7_deleteStudent() {
		studentRepository.deleteAll();
		assertThat(studentRepository.findAll()).isEmpty();
	}
	@Test
	public void t8_deleteSubject() {
		subjectRepository.deleteAll();
		assertThat(subjectRepository.findAll()).isEmpty();
	}
	@Test
	public void t9_deleteGrade() {
		gradeRepository.deleteAll();
		assertThat(gradeRepository.findAll()).isEmpty();
	}
	
	
}
