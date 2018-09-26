package demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostActionTest {

	@Autowired
	private MockMvc mockMvc;

	private static final String JSON_BODY = "{" + //
			"\"firstname\": \"Thorsten\"," + //
			"\"lastname\": \"Maier\"," + //
			"\"type\": \"demo.actions.AddUserAction\"" + //
			"}"; //

	@Test
	public void testDispatch() throws Exception {
		mockMvc.perform(post("/dispatch").content(JSON_BODY)).andExpect(content().string("Added user: Thorsten Maier"));
	}
}
