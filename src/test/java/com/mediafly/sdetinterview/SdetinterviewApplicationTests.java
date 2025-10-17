package com.mediafly.sdetinterview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mediafly.sdetinterview.models.CityDTO;
import com.mediafly.sdetinterview.models.CountryDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SdetInterviewApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to the Mediafly SDET interview!")));
    }

	@Test
	void addingOneCountry() throws Exception {
        CountryDTO canada = new CountryDTO("Canada");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(canada);

        mvc.perform(post("/country/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
	}

    @Test
    void gettingOneCountry() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/country/Canada").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Canada")));
    }

    @Test
    void addingOneCity() throws Exception {
        CountryDTO canada = new CountryDTO("Canada");
        CityDTO toronto = new CityDTO("Toronto", canada);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(canada);

        mvc.perform(post("/country/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        requestJson=ow.writeValueAsString(toronto);

        mvc.perform(post("/city/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/city/Toronto").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Toronto")))
                .andExpect(jsonPath("$.country.name", is("Canada")));

        mvc.perform(MockMvcRequestBuilders.get("/city/?country=Canada").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Toronto")))
                .andExpect(jsonPath("$[0].country.name", is("Canada")));

        mvc.perform(MockMvcRequestBuilders.get("/city/?country=USA").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect( jsonPath( "$", Matchers.empty()));
    }

    @Test
    void addingManyCities() throws Exception {
        CountryDTO canada = new CountryDTO("Canada");
        CityDTO toronto = new CityDTO("Toronto", canada);
        CityDTO hamilton = new CityDTO("Hamilton", canada);

        CountryDTO usa = new CountryDTO("USA");
        CityDTO newYork = new CityDTO("New York", usa);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(canada);

        mvc.perform(post("/country/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        requestJson=ow.writeValueAsString(toronto);

        mvc.perform(post("/city/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/city/Toronto").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Toronto")))
                .andExpect(jsonPath("$.country.name", is("Canada")));


        requestJson=ow.writeValueAsString(hamilton);

        mvc.perform(post("/city/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/city/Hamilton").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Hamilton")))
                .andExpect(jsonPath("$.country.name", is("Canada")));


        // Can't create New York since USA was not created
        requestJson=ow.writeValueAsString(newYork);
        mvc.perform(post("/city/").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is4xxClientError());
    }
}
