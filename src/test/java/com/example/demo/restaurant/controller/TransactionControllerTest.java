package com.example.demo.restaurant.controller;

import com.example.demo.restaurant.model.Category;
import com.example.demo.restaurant.model.Transaction;
import com.example.demo.restaurant.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dummyTransactionsTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dummy-transaction")
                .accept(MediaType.APPLICATION_JSON);

// Methode 1
//        String expected = "[{\"name\":\"TÃ©lÃ©phone\",\"price\":10.0,\"quantity\":1,\"category\":\"EXPENSE\"}," +
//                "{\"name\":\"Internet\",\"price\":20.0,\"quantity\":1,\"category\":\"EXPENSE\"}," +
//                "{\"name\":\"Salary\",\"price\":20.0,\"quantity\":1,\"category\":\"INCOME\"}]";

        String expected = TestUtils.convertToJSON(
                Arrays.asList(
                Transaction.builder().category(Category.EXPENSE).quantity(1).price(10d).name("Telephone").build(),
                Transaction.builder().category(Category.EXPENSE).quantity(1).price(20d).name("Internet").build(),
                Transaction.builder().category(Category.INCOME).quantity(1).price(20d).name("Salary").build()
                )
        );


        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
//               Methode 1 .andExpect(content().string(expected)) celui-là ou celui en dessous, si en dessous expected devient useless
//               Methode 2 .andExpect(content().json("[{name:Telephone,price:10.0,quantity:1,category:EXPENSE}," +
//                        "{name:Internet,price:20.0,quantity:1,category:EXPENSE}," +
//                        "{name:Salary,price:20.0,quantity:1,category:INCOME}]"))
                .andExpect(content().string(expected))
                .andReturn();

    }
}
