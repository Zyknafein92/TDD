package com.example.demo.restaurant.utils;

import com.example.demo.restaurant.model.Item;
import com.example.demo.restaurant.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {

    private static ObjectMapper mapper = new ObjectMapper();

//    public static String convertToJSON(List<Transaction> transactions) throws Exception{
//        return "[" + transactions.stream()
//                .map(TestUtils::getTransactionAsJSONString)
//                .collect(Collectors.joining(",")) +"]";
//    }
//
//    private static String getTransactionAsJSONString(Transaction transaction) {
//        try {
//            return mapper.writeValueAsString(transaction);
//        } catch (JsonProcessingException e) {
//            return "";
//        }
//    }

    public static <T> String convertToJSON(List<T> list) throws Exception{
        return "[" + list.stream()
                .map(TestUtils::getJSONString)
                .collect(Collectors.joining(",")) +"]";
    }

    private static <T> String getJSONString(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static String convertToJson(Item item) {
        try {
            return mapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

}
