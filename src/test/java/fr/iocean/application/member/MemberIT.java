package fr.iocean.application.member;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.service.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MemberIT extends IntegrationTest {


    @Test
    public void test() {
        System.out.println("test ok");
    }

    @Autowired
    MemberService memberService;

    @Test
    public void testCreate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Member m = new Member();
        m.setId(-2L);
        m.setFirstname("coco");
        m.setLastname("l asticot");
        m.setDob(sdf.parse("2001/10/05"));
        m.setEmail("coco@gmail.com");
        m.setAddress1("route de Kaamelott");
        m.setCity("Kaamelott");


        this.mockMvc.perform(post("/api/members").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(jsonHelper.serialize(m))).andExpect(status().isCreated());
        this.mockMvc.perform(get("/api/members")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(status().isOk());
    }

    @Test
    public void testFindOne() throws Exception {
        this.mockMvc.perform(get("/api/members/-1")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstname").value("toto")).andExpect(status().isOk());
    }


    @Test
    public void testFindAll() throws Exception {
        this.mockMvc.perform(get("/api/members/")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findOneNotFound() throws Exception {
        this.mockMvc.perform(get("/api/members/999")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

	@Test
	public void testDelete() throws Exception {
		Member m = memberService.findOne(-1L);
		this.mockMvc.perform(delete("/api/members/-1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/members")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(0))).andExpect(status().isOk());
	}

    @Test
    public void testUpdate() throws Exception {
        Member m = memberService.findOne(-1L);
        m.setFirstname("lolo");

        this.mockMvc.perform(put("/api/members/-1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(jsonHelper.serialize(m))).andExpect(status().isOk());
        this.mockMvc.perform(get("/api/members/-1")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstname").value("lolo")).andExpect(status().isOk());
    }
    @Test
    public void testPreconditionFailed() throws Exception {
        Member m = memberService.findOne(-1L);
        m.setLastname("");

        this.mockMvc
                .perform(put("/api/members/-1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                        .content(jsonHelper.serialize(m)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isPreconditionFailed());
    }

    @Test
    public void testCreatePreconditionFailed() throws Exception {
        Member m = new Member();

        this.mockMvc
                .perform(post("/api/members").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                        .content(jsonHelper.serialize(m)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isPreconditionFailed());
    }

}
