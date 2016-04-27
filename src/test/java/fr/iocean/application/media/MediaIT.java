package fr.iocean.application.media;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.service.MediaService;
import fr.iocean.application.type.model.Type;

public class MediaIT extends IntegrationTest {

	@Autowired
	MediaService mediaService;

	@Test
	public void testTest() throws Exception {
		this.mockMvc.perform(get("/api/medias")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(4))).andExpect(status().isOk());
	}

	@Test
	public void testCreate() throws Exception {
		System.out.println("------------------------------------------------------------------------------\n"
				+ "-------------------------------- TEST CREATE ---------------------------------\n"
				+ "------------------------------------------------------------------------------");
		Media m = new Media();
		m.setTitle("titleTest1");
		m.setAuthor("authorTest1");
		m.setType(1L);

		this.mockMvc.perform(post("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isCreated());
		this.mockMvc.perform(get("/api/medias")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(5))).andExpect(status().isOk());
	}

	@Test
	public void testFindById() throws Exception {
		System.out.println("------------------------------------------------------------------------------\n"
				+ "------------------------------- TEST FINDBYID --------------------------------\n"
				+ "------------------------------------------------------------------------------");
		this.mockMvc.perform(get("/api/medias/10")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.title").value("La tour sombre, le pistolero")).andExpect(status().isOk());
	}

	@Test
	public void testUpdate() throws Exception {
		System.out.println("------------------------------------------------------------------------------\n"
				+ "-------------------------------- TEST UPDATE ---------------------------------\n"
				+ "------------------------------------------------------------------------------");
		Media m = mediaService.findById(10L);
		m.setTitle("Misery");
		this.mockMvc.perform(put("/api/medias/10").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/medias/10").contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.title").value("Misery"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		System.out.println("------------------------------------------------------------------------------\n"
				+ "-------------------------------- TEST DELETE ---------------------------------\n"
				+ "------------------------------------------------------------------------------");
		Media m = mediaService.findById(20L);
		this.mockMvc.perform(get("/api/medias")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(5))).andExpect(status().isOk());
		this.mockMvc.perform(delete("/api/medias/20").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/medias")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(4))).andExpect(status().isOk());
	}

}
