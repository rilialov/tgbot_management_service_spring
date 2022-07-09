package tgbot.management_service.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tgbot.management_service.entity.Task;

import static org.junit.jupiter.api.Assertions.*;

class TasksControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() throws Exception {
        String uri = "/api/v1/tasks/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        Task task = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Task.class);
        assertNotNull(task);
        assertEquals("Create Service connected to telegram", task.getTaskName());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}