package tgbot.management_service.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tgbot.management_service.entity.Report;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReportsControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void findAll() throws Exception {
        String uri = "/api/v1/reports";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void findById() throws Exception {
        String uri = "/api/v1/reports/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        Report report = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Report.class);
        assertNotNull(report);
        assertEquals(864000L, report.getFullTime());
    }

    @Test
    void notFound() throws Exception {
        String uri = "/api/v1/reports/20";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    void create() throws Exception {
        String uri = "/api/v1/reports";
        Report report = new Report(LocalDate.now(), 2L);
        report.setFullTime(777777L);

        String inputJson = super.mapToJson(report);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        Report created = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Report.class);
        assertEquals(2L, created.getUser());
        assertEquals(777777L, created.getFullTime());
    }

    @Test
    void update() throws Exception {
        String uri = "/api/v1/reports/4";
        Report report = new Report(LocalDate.now(), 2L);
        report.setFullTime(777777L);

        String inputJson = super.mapToJson(report);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void delete() throws Exception {
        String uri = "/api/v1/reports/9";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}