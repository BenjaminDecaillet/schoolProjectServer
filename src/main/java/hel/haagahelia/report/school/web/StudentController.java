package hel.haagahelia.report.school.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	
	@RequestMapping(value="/")
    public String index() {	
        return "login";
    }
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

}
