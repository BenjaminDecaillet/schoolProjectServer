package hel.haagahelia.report.school;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	public void saveStudent() {
		Student student = new Student("test","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","ben@mail.com","ADMIN");
		entityManager.persistAndFlush(student);
		assertThat(student.getId()).isNotNull();
	}
	@Test
	public void saveSubject() {
		Subject subject = new Subject("Business Intelligence");
		entityManager.persistAndFlush(subject);
		assertThat(subject.getId()).isNotNull();
	}
	@Test
	public void saveGrade() {
		Grade grade = new Grade("Mid Term exam",1,1);
		entityManager.persistAndFlush(grade);
		assertThat(grade.getId()).isNotNull();
	}
	@Test
	public void deleteStudent() {
		entityManager.persistAndFlush(new Student("test","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","admin@mail.com","USER"));
		entityManager.persistAndFlush(new Student("test 2","$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi","user@mail.com","USER"));
		studentRepository.deleteAll();
		assertThat(studentRepository.findAll()).isEmpty();
	}
	@Test
	public void deleteSubject() {
		entityManager.persistAndFlush(new Subject("Business Intelligence"));
		entityManager.persistAndFlush(new Subject("Server Programming"));
		subjectRepository.deleteAll();
		assertThat(subjectRepository.findAll()).isEmpty();
	}
	@Test
	public void deleteGrade() {
		entityManager.persistAndFlush(new Grade("Mid Term exam",1,1));
		entityManager.persistAndFlush(new Grade("Fianl exam",2,2));
		gradeRepository.deleteAll();
		assertThat(gradeRepository.findAll()).isEmpty();
	}
	@Test
	public void findStudentByUsername() {
		entityManager.persistAndFlush(new Student("john","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","admin@mail.com","USER"));
		entityManager.persistAndFlush(new Student("thomas","$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi","user@mail.com","USER"));
		assertThat(studentRepository.findByUsername("john").getId()).isNotNull();
	}
	@Test
	public void findSubjectsByStudent() {
		Student student = studentRepository.findByUsername("ben");
		assertThat(subjectRepository.findByStudent(student)).isNotNull();
	}
	@Test
	public void findGradeBySubjectt() {
		Student student = studentRepository.findByUsername("ben");
		List<Subject> subjects = subjectRepository.findByStudent(student);
		Subject subject = subjects.get(0);
		assertThat(gradeRepository.findBySubject(subject)).isNotNull();
	}
	
	
}
