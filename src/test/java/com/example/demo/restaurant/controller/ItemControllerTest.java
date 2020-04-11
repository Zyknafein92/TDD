package com.example.demo.restaurant.controller;

import com.example.demo.DemoApplication;
import com.example.demo.restaurant.model.Item;
import com.example.demo.restaurant.repository.ItemRepository;
import com.example.demo.restaurant.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void ItemsTest() throws Exception {
        //init
       Item ipod =  repository.save(
                Item.builder().price(55.5).name("Ipod").build()
        );

        //perform request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/items")
                .accept(MediaType.APPLICATION_JSON);

        String expectedValue = TestUtils.convertToJSON(Arrays.asList(ipod));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(expectedValue))
                .andReturn();

    }

    @Test
    public void addItem() throws Exception {

        Item ipod = Item.builder().price(55.5).name("Ipod").build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertToJson(ipod))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //assertEquals(1, repository.findAll().size());
        assertThat(repository.findAll()).hasSize(1);

    }

    @Test
    public void updateItem() throws  Exception {
        Item ipod =  repository.save(
                Item.builder().price(55.5).name("Ipod").build()
        );

        ipod.setName("Ipod2");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/items/"+ipod.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertToJson(ipod))
                .accept(MediaType.APPLICATION_JSON);


       MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(TestUtils.convertToJson(ipod)))
                .andReturn();

        Item ipod2 = repository.findAll().get(0);

        assertThat(ipod2.getName()).isEqualTo("Ipod2");
    }

    @Test
    public void deleteItem() throws Exception {
        Item ipod =  repository.save(
                Item.builder().price(55.5).name("Ipod").build()
        );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/items/"+ipod.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertThat(repository.findAll()).hasSize(0);
    }

    @Test
    public void deleteInexistingItemTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/items/0")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertThat(repository.findAll()).hasSize(0);
    }
}
