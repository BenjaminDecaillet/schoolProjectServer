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
			// Save demo data to database
			// Add Grades objects and save these to db
			Grade grade1 = new Grade("Mid Term exam",1,1);
			Grade grade2 = new Grade("Final Exam",3,2);
			Grade grade3 = new Grade("Mid Term exam",5,1);
			Grade grade4 = new Grade("Final Exam",4,2);
			Subject BI = new Subject("Business Intelligence");
			Subject IT = new Subject("Informatique");
			subjectRepository.save(BI);
			subjectRepository.save(IT);
			grade1.setSubject(BI);
			grade2.setSubject(BI);
			grade3.setSubject(IT);
			grade4.setSubject(IT);
			gradeRepository.save(grade1);
			gradeRepository.save(grade2);
			gradeRepository.save(grade3);
			gradeRepository.save(grade4);

			// username: kevin password: user
			studentRepository.save(new Student("kevin",
					"$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi","user@mail.com","USER"));
			// username: ben password: admin
			studentRepository.save(new Student("ben",
					"$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","admin@mail.com","ADMIN"));
		};
	}
}
