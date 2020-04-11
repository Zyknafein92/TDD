package com.example.demo.restaurant.controller;

import com.example.demo.restaurant.model.Category;
import com.example.demo.restaurant.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TransactionController {

    @GetMapping("/dummy-transaction")
    public List<Transaction> getTransactions() {
        return Arrays.asList(
                Transaction.builder().category(Category.EXPENSE).quantity(1).price(10d).name("Telephone").build(),
                Transaction.builder().category(Category.EXPENSE).quantity(1).price(20d).name("Internet").build(),
                Transaction.builder().category(Category.INCOME).quantity(1).price(20d).name("Salary").build()
        );
    }
}
