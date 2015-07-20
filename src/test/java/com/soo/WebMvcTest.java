package com.soo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author KHS
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = "test")
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({"server.port=0"})
public class WebMvcTest {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate rest;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Before
    public void setUp() throws Exception {
        rest = new TestRestTemplate();
    }

    @Test
    public void postUser() throws Exception {
        /*
        HtmlForm 데이터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("name", "test");
        params.put("password", "test");
        */
        Map<String, Object> params = new HashMap<>();
        params.put("name", "test");
        params.put("password", "test");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ObjectMapper jackson = new ObjectMapper();
        String body = jackson.writeValueAsString(params);

        HttpEntity request = new HttpEntity(body, headers);

        ResponseEntity<String> response = rest.postForEntity(getBaseUrl() + "/api/users", request, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void getUsers() throws Exception {
        ResponseEntity<List> response = rest.getForEntity(getBaseUrl() + "/api/users", List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<HashMap<String, Object>> persons = response.getBody();
        System.out.println(persons);
    }

    @Test
    public void getUser() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("number", 1);

        ResponseEntity<String> response = rest.getForEntity(getBaseUrl() + "/api/users/{number}", String.class, params);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response.getBody());

        System.out.println(responseJson);
        assertThat(Integer.parseInt(String.valueOf(responseJson.get("number"))), is(1));
    }

    @Test
    public void putUser() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "haha1");
        params.put("password", "haha1");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ObjectMapper jackson = new ObjectMapper();
        String body = jackson.writeValueAsString(params);

        HttpEntity request = new HttpEntity(body, headers);

        ResponseEntity<String> response = rest.exchange(getBaseUrl() + "/api/users/1", HttpMethod.PUT, request, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
