package ru.diasoft.testapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.diasoft.testapp.configuration.TestConfig;
import ru.diasoft.testapp.dto.ProductForResponse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
@Rollback
@Transactional
class ProductControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected String getResourceFileContextAsString(String filepath) throws IOException {
        File file = getResourceFile(filepath);
        return FileUtils.readFileToString(file, Charset.availableCharsets().get("UTF-8"));
    }

    protected File getResourceFile(String filepath) throws IOException {
        return resourceLoader.getResource(filepath).getFile();
    }

    @Test
    void save() throws Exception {
        MvcResult res = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:product/bodyForSave.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
    }

    @Test
    void delete() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.delete("/product/{id}", "dfdd1d10-d7c6-4824-830f-2716bac70a86")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    void getById() throws Exception {
        MvcResult res = mockMvc.perform(get("/product/23e552ca-afed-432f-8b6f-f9b013dc1f9d")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String json = res.getResponse().getContentAsString();
        ProductForResponse actual = objectMapper.readValue(json, ProductForResponse.class);
        String data = getResourceFileContextAsString("classpath:product/getById.json");
        ProductForResponse expected = objectMapper.readValue(data, ProductForResponse.class);

        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getAll() throws Exception {
        MvcResult res = mockMvc.perform(get("/product/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        ProductForResponse[] actual = objectMapper.readValue(res.getResponse().getContentAsString(), ProductForResponse[].class);
        String data = getResourceFileContextAsString("classpath:product/getAll.json");
        ProductForResponse[] expected = objectMapper.readValue(data, ProductForResponse[].class);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        assertEquals(Arrays.hashCode(expected), Arrays.hashCode(actual));
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    void update() throws Exception {
        MvcResult res = mockMvc.perform(put("/product/70290a60-fe57-46cd-b110-105897bd8664")
                .contentType(MediaType.APPLICATION_JSON).content(getResourceFileContextAsString("classpath:product/bodyForUpdate.json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void sum() throws Exception {
        MvcResult res = mockMvc.perform(get("/product/getSumAmountQuery/name2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String expected = res.getResponse().getContentAsString();
        String actual = "6000";
        assertEquals(actual, expected);
    }
}