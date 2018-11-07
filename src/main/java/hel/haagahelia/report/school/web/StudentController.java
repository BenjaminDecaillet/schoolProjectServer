package hel.haagahelia.report.school.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hel.haagahelia.report.school.domain.Student;
import hel.haagahelia.report.school.domain.StudentRepository;
import hel.haagahelia.report.school.domain.Subject;

@Controller
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(value="/")
    public String index() {	
        return "login";
    }
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    /*
	 * *****************************************
	 * REST Part
	 *******************************************
	 */

	/**
	 * Get all the students 
	 * @return json List of students
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public @ResponseBody List<Student> students() {
		return (List<Student>) studentRepository.findAll();
	}
	/**
	 * Get a student by it's id
	 * @return json of a student
	 */
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public @ResponseBody Student findbyId(@PathVariable("id") Long id) {
		return  studentRepository.findById(id).get();
	}
	/**
	 * Delete a student by it's id
	 * @return string on successful delete
	 */
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		studentRepository.deleteById(id);
		return "Student with id "+id+" has been removed";
	}
	/**
	 * Save a student to the repository
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Student student) {
		Student savedstudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedstudent.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}