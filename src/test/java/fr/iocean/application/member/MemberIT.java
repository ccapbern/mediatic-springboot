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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        m.setFirstname("coco");
        m.setLastname("l asticot");
        m.setDob(sdf.parse("2001/10/05"));
        m.setEmail("coco@gmail.com");
        m.setAddress("route de Kaamelott");
        m.setCity("Kaamelott");


        this.mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(jsonHelper.serialize(m))).andExpect(status().isCreated());
        this.mockMvc.perform(get("/api/member")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(status().isOk());
    }

    @Test
    public void testFindOne() throws Exception {
        this.mockMvc.perform(get("/api/member/1")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$").value("coco")).andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Member m = memberService.findOne(1L);
        m.setFirstname("toto");

        this.mockMvc.perform(put("/api/member/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(jsonHelper.serialize(m))).andExpect(status().isOk());
        this.mockMvc.perform(get("/api/member/1")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstname").value("toto")).andExpect(status().isOk());
    }

//	@Test
//	public void testDelete() throws Exception {
//		User u = userService.findOne(1L);
////MarchePOOO
//		this.mockMvc.perform(delete("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//				.content(jsonHelper.serialize(u))).andExpect(status().isOk());
//		this.mockMvc.perform(get("/api/users")).andDo(MockMvcResultHandlers.print())
//				.andExpect(jsonPath("$", hasSize(4))).andExpect(status().isOk());
//	}

//    @Test
//    public void testPreconditionFailed() throws Exception {
//        Member u = new Member();
//
//        this.mockMvc
//                .perform(post("/api/users/").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//                        .content(jsonHelper.serialize(u)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$[*].field", Matchers.containsInAnyOrder("password", "login")))
//                .andExpect(jsonPath("$[0].objectName").value("user"))
//                .andExpect(jsonPath("$[*].code", Matchers.containsInAnyOrder("NotBlank", "NotBlank")))
//                .andExpect(status().isPreconditionFailed());
//    }
}
