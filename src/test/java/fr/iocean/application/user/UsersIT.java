package fr.iocean.application.user;

import fr.iocean.application.IntegrationTest;
import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsersIT extends IntegrationTest {

    @Test
    public void findAll() throws Exception {
        this.mockMvc.perform(get("/api/users/")).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findOne() throws Exception {
        this.mockMvc.perform(get("/api/users/1")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void findOneNotFound() throws Exception {
        this.mockMvc.perform(get("/api/users/999")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}
