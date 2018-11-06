package hel.haagahelia.report.school.web;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hel.haagahelia.report.school.domain.Grade;
import hel.haagahelia.report.school.domain.GradeRepository;
import hel.haagahelia.report.school.domain.Student;
import hel.haagahelia.report.school.domain.StudentRepository;
import hel.haagahelia.report.school.domain.Subject;
import hel.haagahelia.report.school.domain.SubjectRepository;

@Controller
public class SubjectController {
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired 
	private GradeRepository gradeRepository;
	@Autowired 
	private StudentRepository studentRepository;
	
	
	/**
	 * List of subjects with thymeleaf
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/subjectlist")
	public String subjectlist(Model model, Principal principal) {
		String username = principal.getName();
		Student connectedStudent = studentRepository.findByUsername(username);
		List<Subject> subjects = (List<Subject>) subjectRepository.findByStudent(connectedStudent);
		for (Subject subject : subjects) {
//			System.out.println("************************");
//			System.out.println(subject.getName());
			List<Grade> gradesofSubject = subject.getGrades();
			if(gradesofSubject.size()>0){
				int size = 0;
				int sum = 0;

				for (Grade grade : gradesofSubject) {
					sum = sum + (grade.getValue()*grade.getWeight());
					size= size + grade.getWeight();
				}
				double avg =  (double) sum/(double) size;
				subject.setAverage(avg);
				subjectRepository.save(subject);
			}
//			System.out.println("************************");
		}
		model.addAttribute("subjects", (List<Subject>) subjectRepository.findByStudent(connectedStudent));
		return "subjectlist";
	}
	/**
	 * Thymeleaf edit a subject by it's id
	 * @param subjectid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editsubject/{id}", method = RequestMethod.GET)
	public String editsubject(@PathVariable("id") Long subjectid, Model model) {
		model.addAttribute("subject", subjectRepository.findById(subjectid));
		Subject subject = subjectRepository.findById(subjectid).get();
		model.addAttribute("grades", gradeRepository.findBySubject(subject));
		return "editsubject";
	}
	/**
	 * Thymeleaf add a new subject
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addsubject")
	public String addsubject(Model model) {
		model.addAttribute("subject", new Subject());
		return "addsubject";
	}
	/**
	 * Thymeleaf save subject to repository
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/savesubject", method = RequestMethod.POST)
	public String savesubject(Subject subject, Principal principal) {
		String username = principal.getName();
		Student connectedStudent = studentRepository.findByUsername(username);
		subject.setStudent(connectedStudent);
		subjectRepository.save(subject);
		return "redirect:subjectlist";
	}
	/**
	 * Thymeleaf Delete a subject by his id
	 * @param subjectid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSubject(@PathVariable("id") Long subjectid, Model model) {
		subjectRepository.deleteById(subjectid);
		return "redirect:../subjectlist";
	}

	/*
	 * *****************************************
	 * REST Part
	 *******************************************
	 */

	/**
	 * Get all the subjects 
	 * @return json List of subjects
	 */
	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public @ResponseBody List<Subject> subjects() {
		return (List<Subject>) subjectRepository.findAll();
	}
	/**
	 * Get a subject by it's id
	 * @return json of a subject
	 */
	@RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
	public @ResponseBody Subject findbyId(@PathVariable("id") Long id) {
		return  subjectRepository.findById(id).get();
	}
	/**
	 * Delete a subject by it's id
	 * @return string on successful delete
	 */
	@RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		subjectRepository.deleteById(id);
		return "Subject with id "+id+" has been removed";
	}
	/**
	 * Save a subject to the repository
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/subject", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Subject subject) {
		Subject savedsubject = subjectRepository.save(subject);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedsubject.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
