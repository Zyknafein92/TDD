package com.example.demo.restaurant.business;

import com.example.demo.restaurant.business.repository.TransationRepository;
import com.example.demo.restaurant.model.Category;
import com.example.demo.restaurant.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceMockTest {

    @Mock
    TransationRepository repository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    public void noExpenseInDB_ReturnZero(){
        when(repository.getExpenseTransactions()).thenReturn(new ArrayList<>());
        assertEquals(new Double(0), transactionService.getTotalExpense());
    }

    @Test
    public void oneExpenseIn_DB_ReturnExpenseValue(){
        when(repository.getExpenseTransactions()).thenReturn(
                Arrays.asList(
                        Transaction.builder()
                                .price(10d)
                                .category(Category.EXPENSE)
                                .quantity(1)
                                .build()
                )
        );
        assertEquals(new Double(10), transactionService.getTotalExpense());
    }

    @Test
    public void serveralExpenses_ReturnTotalExpense(){
     // ajouter plusieurs expenses, et vérifier le total.
        when(repository.getExpenseTransactions()).thenReturn(
                Arrays.asList(
                        Transaction.builder()
                                .price(30d)
                                .category(Category.EXPENSE)
                                .quantity(1)
                                .build(),
                        Transaction.builder()
                                .price(30d)
                                .category(Category.EXPENSE)
                                .quantity(1)
                                .build(),
                        Transaction.builder()
                                .price(30d)
                                .category(Category.EXPENSE)
                                .quantity(1)
                                .build(),
                        Transaction.builder()
                                .price(10d)
                                .category(Category.EXPENSE)
                                .quantity(1)
                                .build()
                )
        );
        assertEquals(new Double(100), transactionService.getTotalExpense());
    }
}
