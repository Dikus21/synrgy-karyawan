package com.aplikasi.karyawan.testing;

import com.aplikasi.karyawan.dto.ResponseDto;
import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingRestTemplateKaryawan {
    @Autowired
    private TestRestTemplate restTemplate;
    /*
    jika test api nya dari local : API Harus RUNNING
     */

    @Test
    public void listSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
//        headers.set("Content-Type", "application/json");

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/list-spec?size=10&page=0", HttpMethod.GET, null, String.class);
        System.out.println("response = " + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void saveSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "    \"name\":\"Yolanda\",\n" +
                "    \"address\":\"jakarta\",\n" +
                "    \"dob\":\"2023-01-01\",\n" +
                "    \"status\":\"active\",\n" +
                "    \"detailKaryawan\":{\n" +
                "        \"nik\":\"123124\",\n" +
                "        \"npwp\":\"120394\"\n" +
                "    }\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(bodyTesting, headers);
        System.out.println("bodyTesting = " + bodyTesting);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/save", HttpMethod.POST, entity, String.class);
        System.out.println("response = " + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());

        Karyawan karyawanSaved = null;
        ResponseDto responseDto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode root = mapper.readTree(exchange.getBody());
            JsonNode data = root.path("data");
            String message = root.get("message").asText();
            int status = root.get("code").asInt();
            karyawanSaved = mapper.treeToValue(data, Karyawan.class);
            responseDto = mapper.treeToValue(data, ResponseDto.class);
            System.out.println("data dto = " + message);
            System.out.println("data dto = " + status);
            System.out.println("data dto = " + data);
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
        }
        System.out.println("date = " + karyawanSaved.getCreatedDate());
        System.out.println("date = " + responseDto.getCreatedDate());
        assertNotNull("Response body is null", karyawanSaved);
        assertEquals("Name does not match", "Yolanda", karyawanSaved.getName());
        assertEquals("NPWP does not match", "120394", karyawanSaved.getDetailKaryawan().getNpwp());
    }

    @Test
    public void deleteSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTestingSave = "{\n" +
                "    \"name\":\"Yolanda\",\n" +
                "    \"address\":\"jakarta\",\n" +
                "    \"dob\":\"2023-01-01\",\n" +
                "    \"status\":\"active\",\n" +
                "    \"detailKaryawan\":{\n" +
                "        \"nik\":\"123124\",\n" +
                "        \"npwp\":\"120394\"\n" +
                "    }\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(bodyTestingSave, headers);
        System.out.println("bodyTestingSave = " + bodyTestingSave);
        ResponseEntity<String> exchangeSave = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/save", HttpMethod.POST, entity, String.class);
        System.out.println("Response = " + exchangeSave.getBody());
        assertEquals(HttpStatus.OK, exchangeSave.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        Karyawan karyawanSaved = null;
        try {
            JsonNode root = mapper.readTree(exchangeSave.getBody());
            JsonNode data = root.path("data");
            karyawanSaved = mapper.treeToValue(data, Karyawan.class);
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
        }
        String bodyTestingDelete = "{\n" +
                "\"id\":\"" + karyawanSaved.getId() + "\"\n" +
                "}";
        entity = new HttpEntity<>(bodyTestingDelete, headers);
        System.out.println("bodyTestingDelete = " + bodyTestingDelete);
        ResponseEntity<String> exchangeDelete = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/delete", HttpMethod.DELETE, entity, String.class);
        System.out.println("Response = " + exchangeDelete.getBody());
        assertEquals(HttpStatus.OK, exchangeDelete.getStatusCode());
        try {
            JsonNode root = mapper.readTree(exchangeDelete.getBody());
            String deletedDate = root.get("data").get("deletedDate").asText();
            System.out.println("Delted Date = " + deletedDate);
        } catch (Exception e) {
            System.out.println("Error = " + e.getMessage());
        }
    }

    @Test
    public void getByIdSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        //headers.set("Content-Type", "application/json");
        Integer id = 502;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/"+id, HttpMethod.GET, null, String.class);
        System.out.println("Response = " + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void updateSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTestingSave = "{\n" +
                "    \"name\":\"Yolanda\",\n" +
                "    \"address\":\"jakarta\",\n" +
                "    \"dob\":\"2023-01-01\",\n" +
                "    \"status\":\"active\",\n" +
                "    \"detailKaryawan\":{\n" +
                "        \"nik\":\"123124\",\n" +
                "        \"npwp\":\"120394\"\n" +
                "    }\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(bodyTestingSave, headers);
        System.out.println("bodyTestingSave = " + bodyTestingSave);
        ResponseEntity<String> exchangeSave = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/save", HttpMethod.POST, entity, String.class);
        System.out.println("Response = " + exchangeSave.getBody());
        assertEquals(HttpStatus.OK, exchangeSave.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        Karyawan karyawanSaved = null;
        try {
            JsonNode root = mapper.readTree(exchangeSave.getBody());
            JsonNode data = root.path("data");
            karyawanSaved = mapper.treeToValue(data, Karyawan.class);
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
        }
        String bodyTestingUpdate = "{\n" +
                "   \"id\":" + karyawanSaved.getId() + ",\n" +
                "    \"name\":\"Boby\",\n" +
                "    \"address\":\"Semarang\"\n" +
                "}";
        entity = new HttpEntity<>(bodyTestingUpdate, headers);
        System.out.println("bodyTestingUpdate = " + bodyTestingUpdate);
        ResponseEntity<String> exchangeUpdate = restTemplate.exchange("http://localhost:8081/api/v1/karyawan/update", HttpMethod.PUT, entity, String.class);
        System.out.println("Response = " + exchangeUpdate.getBody());
        assertEquals(HttpStatus.OK, exchangeUpdate.getStatusCode());
    }
}
