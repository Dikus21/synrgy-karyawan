package com.aplikasi.karyawan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class KaryawanControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/v1/karyawan/save").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            System.out.println("result + " + result);
        });
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getById() {
    }

    @Test
    void listQuizHeaderSpec() throws Exception{
        mockMvc.perform(get("/v1/karyawan/list-spec?page=0&size=10").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            System.out.println("result+"+result);
//            assertNotNull(merchants);
//            assertTrue(merchants.isEmpty());
        });
    }
}