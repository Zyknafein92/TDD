package com.example.demo.restaurant.business;

import com.example.demo.restaurant.model.Category;
import com.example.demo.restaurant.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TransactionServiceTest {
    TransactionService service;

    @BeforeEach
    public void setup() {
        service = new TransactionService();
    }

    private void assertResult(List<Transaction> transactions, double v) {
        Double actualResult = service.getTotalIncome(transactions);
        Double expectedResult = v;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void emptyOrNoIncome_ReturnZero() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().category(Category.EXPENSE).build()
        );

        assertResult(transactions, 0.0);
    }

    @Test
    public void oneIncome_ReturnIncomeValue(){
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder()
                        .category(Category.INCOME)
                        .price(10d)
                        .quantity(1)
                        .build()
        );

        assertResult(transactions, 10.0);

    }



    @Test
    public void multipleIncome_multipleExpense_ReturnTotalIncome(){
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder()
                        .category(Category.INCOME)
                        .price(10d)
                        .quantity(1)
                        .build(),
                Transaction.builder()
                        .category(Category.INCOME)
                        .price(30d)
                        .quantity(3)
                        .build(),
                Transaction.builder()
                        .category(Category.EXPENSE)
                        .price(10d)
                        .quantity(1)
                        .build()
        );
        assertResult(transactions, 100d);
    }

    @Test
    public void nullList_ThrowsIllegalArgumentException() {
        try {
            service.getTotalIncome(null);
        } catch (IllegalArgumentException exception) {
            assertThat(exception.getMessage()).isEqualTo("List should not be null");
        }
    }
}
