package com.example.demo.restaurant.business;

import com.example.demo.restaurant.business.repository.TransationRepository;
import com.example.demo.restaurant.model.Category;
import com.example.demo.restaurant.model.Transaction;

import java.util.List;

public class TransactionService {

    private TransationRepository transationRepository;

    public Double getTotalIncome(List<Transaction> transactions) {
//        Double total = 0.0;
//        Double sum;
//        Double tmp;
//        for (Transaction transaction: transactions) {
//            if(transaction.getCategory().equals(Category.INCOME)) {
//                sum = transaction.getPrice() * transaction.getQuantity();
//                tmp = sum;
//                total = tmp + total;
//            }
//        }
        if (transactions == null) {
            throw new IllegalArgumentException("List should not be null");
        }
        return transactions.stream()
                .filter(transaction -> transaction.getCategory().equals(Category.INCOME))
                .map(transaction -> transaction.getPrice() * transaction.getQuantity())
                .reduce(0d, Double::sum);
    }

    public Double getTotalExpense() {
        return getTotalExpense(transationRepository.getExpenseTransactions());
    }

    private Double getTotalExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getCategory().equals(Category.EXPENSE))
                .map(transaction -> transaction.getPrice() * transaction.getQuantity())
                .reduce(0d, Double::sum);
    }

}
