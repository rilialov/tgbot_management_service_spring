package tgbot.management_service.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
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
    void findAll() throws Exception {
        String uri = "/api/v1/tasks";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        Page<Task> tasks = super.mapPageFromJson(mvcResult.getResponse().getContentAsString(), Task.class);
        assertNotNull(tasks);
        assertTrue(tasks.getTotalElements() > 0);
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
    void notFound() throws Exception {
        String uri = "/api/v1/tasks/10";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Could not find task 10");
    }


    @Test
    void create() throws Exception {
        String uri = "/api/v1/tasks";
        Task task = new Task("Test Task Name", "Test Task Note");

        String inputJson = super.mapToJson(task);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        Task created = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Task.class);
        assertNotNull(created);
        assertEquals("Test Task Name", created.getTaskName());
        assertEquals("Test Task Note", created.getTaskNote());
    }

    @Test
    void update() throws Exception {
        String uri = "/api/v1/tasks/3";
        Task task = new Task("Changed Name", "Changed Note");

        String inputJson = super.mapToJson(task);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void delete() throws Exception {
        String uri = "/api/v1/tasks/4";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}