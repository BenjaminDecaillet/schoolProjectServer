package hel.haagahelia.report.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hel.haagahelia.report.school.domain.Grade;
import hel.haagahelia.report.school.domain.Subject;
import hel.haagahelia.report.school.domain.GradeRepository;
import hel.haagahelia.report.school.domain.Student;
import hel.haagahelia.report.school.domain.StudentRepository;
import hel.haagahelia.report.school.domain.SubjectRepository;


@SpringBootApplication
public class SchoolApplication {

	private static final Logger log = LoggerFactory.getLogger(SchoolApplication.class);
	@Autowired 
	private GradeRepository gradeRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
//			// Save demo data to database
//			// username: kevin password: user
//			Student student1 = new Student("kevin","$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi","kevin@mail.com","USER");
			// username: ben password: admin
			Student student2 = new Student("ben","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","ben@mail.com","USER");
//			studentRepository.save(student1);
			studentRepository.save(student2);
//			Grade grade1 = new Grade("Mid Term exam",1,1);
//			Grade grade2 = new Grade("Final Exam",3,2);
//			Grade grade3 = new Grade("Mid Term exam",5,1);
//			Grade grade4 = new Grade("Final Exam",4,2);
//			Subject BI = new Subject("Business Intelligence",student1);
//			Subject IT = new Subject("Informatique",student1);
//			subjectRepository.save(BI);
//			subjectRepository.save(IT);
//			grade1.setSubject(BI);
//			grade2.setSubject(BI);
//			grade3.setSubject(IT);
//			grade4.setSubject(IT);
//			gradeRepository.save(grade1);
//			gradeRepository.save(grade2);
//			gradeRepository.save(grade3);
//			gradeRepository.save(grade4);
//			Grade grade5 = new Grade("Mid Term exam",2,1);
//			Grade grade6 = new Grade("Final Exam",4,2);
//			Grade grade7 = new Grade("Mid Term exam",3,1);
//			Grade grade8 = new Grade("Final Exam",3,2);
//			Subject ECO = new Subject("Economy",student2);
//			Subject DB = new Subject("Database",student2);
//			subjectRepository.save(ECO);
//			subjectRepository.save(DB);
//			grade5.setSubject(ECO);
//			grade6.setSubject(ECO);
//			grade7.setSubject(DB);
//			grade8.setSubject(DB);
//			gradeRepository.save(grade5);
//			gradeRepository.save(grade6);
//			gradeRepository.save(grade7);
//			gradeRepository.save(grade8);
		};
	}
}
