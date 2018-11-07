package hel.haagahelia.report.school;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hel.haagahelia.report.school.web.GradeController;
import hel.haagahelia.report.school.web.StudentController;
import hel.haagahelia.report.school.web.SubjectController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolApplicationTests {
	
	@Autowired
	private GradeController gradeController;
	@Autowired
	private StudentController studentController;
	@Autowired
	private SubjectController subjectController;
	
	@Test
	public void studentControllerLoads() {
		assertThat(studentController).isNotNull();		
	}
	
	@Test
	public void subjectControllerLoads() {
		assertThat(subjectController).isNotNull();		
	}
	
	@Test
	public void gradeControllerLoads() {
		assertThat(gradeController).isNotNull();		
	}

}
