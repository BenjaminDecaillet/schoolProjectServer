package hel.haagahelia.report.school;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolRestTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAuthentication() throws Exception {
		//TODO: MockMvc still has problem with the posts! more search on documentation that rest api is working
		// Testing authentication with correct credentials
		this.mockMvc.perform(post("/")
			.content("{\"username\":\"ben\", \"password\":\"admin\"}"))
			.andExpect(status().isOk())
			.andExpect(redirectedUrl("http://localhost/subjectlist"));
	}
	
	@Test
	public void getGrades_thenStatus302_RedirectLogin()
	  throws Exception {
	    mockMvc.perform(get("/grades")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().is(302))
	      .andExpect(redirectedUrl("http://localhost/login"));
	}
	

}
