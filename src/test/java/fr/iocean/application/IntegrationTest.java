package fr.iocean.application;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.iocean.application.helper.JsonHelper;
import fr.iocean.application.media.model.Media;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FilRougeApplication.class)
@WebAppConfiguration
public class IntegrationTest {
	
	@Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    protected JsonHelper jsonHelper = new JsonHelper();

    @PostConstruct
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
	
    @Test
	public void testCreate() throws Exception {
		Media m = new Media();
		m.setId(1L);
		m.setTitle("title test1");
		m.setAuthor("author test1");
		
		this.mockMvc.perform(post("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isCreated());
		this.mockMvc.perform(get("/api/medias")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(status().isOk());
	}
}
