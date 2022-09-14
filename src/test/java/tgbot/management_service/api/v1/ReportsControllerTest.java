package tgbot.management_service.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tgbot.management_service.entity.Report;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportsControllerTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(ReportsControllerTest.class);

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
        Page<Report> reports = super.mapPageFromJson(mvcResult.getResponse().getContentAsString(), Report.class);
        assertNotNull(reports);
        assertTrue(reports.getTotalElements() > 0);

        List<Report> reportList = reports.getContent();
        for (int i = 0; i < reportList.size(); i++) {
            logger.info(String.valueOf(reportList.get(i)));
        }
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
        logger.info(report.toString());
    }

    @Test
    void notFound() throws Exception {
        String uri = "/api/v1/reports/20";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Could not find report 20");
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
        assertNotNull(created);
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