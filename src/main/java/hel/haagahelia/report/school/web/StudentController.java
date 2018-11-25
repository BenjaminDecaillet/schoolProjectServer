package hel.haagahelia.report.school.web;

import java.net.URI;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hel.haagahelia.report.school.domain.Student;
import hel.haagahelia.report.school.domain.StudentRepository;
import hel.haagahelia.report.school.domain.Subject;

@Controller
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String index() {	
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.GET)
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
	@RequestMapping(value = "/api/students", method = RequestMethod.GET)
	public @ResponseBody Iterable<Student> getStudents() {
		return studentRepository.findAll();
	}
	/**
	 * Get a student by it's id
	 * @return json of a student
	 */
	@RequestMapping(value = "/api/student/{id}", method = RequestMethod.GET)
	public @ResponseBody Student findbyId(@PathVariable("id") Long id) {
		return  studentRepository.findById(id).get();
	}
	/**
	 * Get a student by it's username
	 * @return json of a student
	 */
	@RequestMapping(value = "/api/studentByUsername/{username}", method = RequestMethod.GET)
	public @ResponseBody Student findbyUsername(@PathVariable("username") String username) {
		return  studentRepository.findByUsername(username);
	}
	/**
	 * Delete a student by it's id
	 * @return string on successful delete
	 */
	@RequestMapping(value = "/api/student/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		studentRepository.deleteById(id);
		return "{\"message\":\"Student with id "+id+" has been removed\"}";
	}
	/**
	 * Save a student to the repository
	 * @param student
	 * @return Student
	 */
	@RequestMapping(value = "/api/student", method = RequestMethod.POST)
	public @ResponseBody Student save(@RequestBody String jsonStudent) {
		JSONObject jsonObj;
		Student student =  new Student();
		try {
			jsonObj = new JSONObject(jsonStudent);
			student.setUsername(jsonObj.getString("username"));
			student.setPassword(jsonObj.getString("password"));
			student.setEmail(jsonObj.getString("email"));
			student.setRole(jsonObj.getString("role"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Student savedstudent = studentRepository.save(student);
		savedstudent.setSubjects(new ArrayList<Subject>());
		studentRepository.save(savedstudent);

		return studentRepository.findById(savedstudent.getId()).get();
	}

}
