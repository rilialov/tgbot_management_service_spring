package tgbot.management_service.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tgbot.management_service.entity.Task;
import tgbot.management_service.entity.Tracking;
import tgbot.management_service.repository.TaskRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TrackingControllerTest extends AbstractTest {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void findAll() throws Exception {
        String uri = "/api/v1/tracking";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        Page<Tracking> tracking = super.mapPageFromJson(mvcResult.getResponse().getContentAsString(), Tracking.class);
        assertNotNull(tracking);
        assertTrue(tracking.getTotalElements() > 0);
    }

    @Test
    void findById() throws Exception {
        String uri = "/api/v1/tracking/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        Tracking tracking = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Tracking.class);
        assertNotNull(tracking);
        assertEquals("Adding connection to users service", tracking.getTrackingNote());
    }

    @Test
    void notFound() throws Exception {
        String uri = "/api/v1/tracking/10";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Could not find tracking 10");
    }

    @Test
    void create() throws Exception {
        String uri = "/api/v1/tracking";
        Optional<Task> optional = taskRepository.findById(1L);
        assertTrue(optional.isPresent());
        Tracking tracking = new Tracking("Test Note", optional.get(), 1L);

        String inputJson = super.mapToJson(tracking);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        Tracking created = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Tracking.class);
        assertNotNull(created);
        assertEquals("Test Note", created.getTrackingNote());
    }

    @Test
    void update() throws Exception {
        String uri = "/api/v1/tracking/4";
        Tracking tracking = new Tracking();
        tracking.setTrackingNote("Changed Note");

        String inputJson = super.mapToJson(tracking);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void delete() throws Exception {
        String uri = "/api/v1/tracking/6";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}