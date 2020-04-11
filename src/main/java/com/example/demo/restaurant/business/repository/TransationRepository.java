package com.example.demo.restaurant.business.repository;


import com.example.demo.restaurant.model.Transaction;

import java.util.List;

public interface TransationRepository {

    List<Transaction> getIncomeTransactions();

    List<Transaction> getExpenseTransactions();
}
