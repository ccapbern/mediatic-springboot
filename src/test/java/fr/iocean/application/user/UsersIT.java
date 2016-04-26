package fr.iocean.application.user;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.service.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsersIT extends IntegrationTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindAll() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testFindOne() throws Exception {
        this.mockMvc.perform(get("/api/users/-1")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testFindOneNotFound() throws Exception {
        this.mockMvc.perform(get("/api/users/999")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreate()  throws Exception{
        User user = new User();
        user.setLogin("Test");
        user.setPassword("Test");
        user.setEmail("test@test.com");

        this.mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(jsonHelper.serialize(user))).andExpect((status().isCreated()));
        this.mockMvc.perform(get("/api/users")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testCreatePreconditionFailed() throws Exception {
        User user = new User();
        user.setLogin("Test");
        user.setPassword("Test");

        this.mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(jsonHelper.serialize(user))).andExpect((status().isPreconditionFailed()));
        this.mockMvc.perform(get("/api/users")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userService.findOne(-1L);
        user.setLogin("Coco");

        this.mockMvc.perform(put("/api/users/-1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(jsonHelper.serialize(user))).andExpect(status().isOk());
        this.mockMvc.perform(get("/api/users/-1")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.login", IsEqual.equalTo("Coco")));
    }

    @Test
    public void testUpdatePreconditionFailed() throws Exception {
        User user = userService.findOne(-1L);
        user.setLogin("");

        this.mockMvc.perform(put("/api/users/-1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(jsonHelper.serialize(user))).andExpect(status().isPreconditionFailed());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/api/users/-2")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/users/-2")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
        this.mockMvc.perform(get("/api/users")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/users/999")).andDo(MockMvcResultHandlers.print())
                .andExpect((status().isNotFound()));
    }
}
