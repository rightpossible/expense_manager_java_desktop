/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.expense_manager;

/**
 *
 * @author MrRight
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ExpenseTotal {
    public static double getTotalExpense() {
        try {
            // Read the JSON file contents
            String json = new String(Files.readAllBytes(Paths.get("expenses.json")));

            // Parse JSON array into a list of ExpenseIncome objects
            List<ExpenseIncome> expenses = new Gson().fromJson(json, new TypeToken<List<ExpenseIncome>>(){}.getType());

            // Calculate total expense
            return expenses.stream()
                           .filter(expense -> "expense".equalsIgnoreCase(expense.getType())) // Filter expenses only
                           .mapToDouble(ExpenseIncome::getAmount)
                           .sum();
        } catch (IOException e) {
            // Handle file reading or JSON parsing errors
            e.printStackTrace();
            return -1; // Return -1 to indicate an error
        }
    }

    public static double getTotalIncome() {
        try {
            // Read the JSON file contents
            String json = new String(Files.readAllBytes(Paths.get("expenses.json")));

            // Parse JSON array into a list of ExpenseIncome objects
            List<ExpenseIncome> incomes = new Gson().fromJson(json, new TypeToken<List<ExpenseIncome>>(){}.getType());

            // Calculate total income
            return incomes.stream()
                         .filter(income -> "income".equalsIgnoreCase(income.getType())) // Filter incomes only
                         .mapToDouble(ExpenseIncome::getAmount)
                         .sum();
        } catch (IOException e) {
            // Handle file reading or JSON parsing errors
            e.printStackTrace();
            return -1; // Return -1 to indicate an error
        }
    }

    public static double getBalance() {
        double totalExpense = getTotalExpense();
        double totalIncome = getTotalIncome();

        // Calculate balance
        return totalIncome - totalExpense;
    }
}

