package hel.haagahelia.report.school.web;

import java.net.URI;
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
import hel.haagahelia.report.school.domain.SubjectRepository;

@Controller
public class GradeController {

	@Autowired 
	private GradeRepository gradeRepository;
	@Autowired
	private SubjectRepository subjectRepository;

	/**
	 * Thymeleaf edit a grade by it's id
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editgrade/{id}", method = RequestMethod.GET)
	public String editgrade(@PathVariable("id") Long gradeid, Model model) {
		model.addAttribute("grade", gradeRepository.findById(gradeid));
		return "editgrade";
	}
	/**
	 * Thymeleaf add a new grade
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addgrade/{id}")
	public String addgrade(@PathVariable("id") Long subjectid,Model model) {
		Grade grade = new Grade();
		grade.setSubject(subjectRepository.findById(subjectid).get());
		model.addAttribute("grade", grade);
		return "addgrade";
	}
	/**
	 * Thymeleaf save grade to repository
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/savegrade", method = RequestMethod.POST)
	public String savegrade(Grade grade) {
		gradeRepository.save(grade);
		return "redirect:editsubject/"+grade.getSubject().getId();
	}
	/**
	 * Thymeleaf Delete a grade by it's id
	 * @param gradeid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deletegrade/{id}", method = RequestMethod.GET)
	public String deletegrade(@PathVariable("id") Long gradeid, Model model) {
		long subjectid = gradeRepository.findById(gradeid).get().getSubject().getId();
		gradeRepository.deleteById(gradeid);
		return "redirect:../editsubject/"+subjectid;
	}
	/*
	 * *****************************************
	 * REST Part
	 *******************************************
	 */
	/**
	 * Get all the grades 
	 * @return json List of grades
	 */
	@RequestMapping(value = "/api/grades", method = RequestMethod.GET)
	public @ResponseBody List<Grade> grades() {
		return (List<Grade>) gradeRepository.findAll();
	}
	/**
	 * Get a grade by it's id
	 * @return json of a grade
	 */
	@RequestMapping(value = "/api/grade/{id}", method = RequestMethod.GET)
	public @ResponseBody Grade findbyId(@PathVariable("id") Long id) {
		return  gradeRepository.findById(id).get();
	}
	/**
	 * Get grades of subject by it's id
	 * @return json list of grades
	 */
	@RequestMapping(value = "/api/gradesOfSubject/{subjectid}", method = RequestMethod.GET)
	public @ResponseBody List<Grade> findSubjectsOfStudent(@PathVariable("subjectid") Long subjectid) {
		return  gradeRepository.findBySubject(subjectRepository.findById(subjectid).get());
	}
	/**
	 * Delete a grade by it's id
	 * @return string on successful delete
	 */
	@RequestMapping(value = "/api/grade/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		gradeRepository.deleteById(id);
		return "Grade with id "+id+" has been removed";
	}
	/**
	 * Save a grade to the repository
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/api/grade", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Grade grade) {
		Grade savedGrade = gradeRepository.save(grade);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedGrade.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
