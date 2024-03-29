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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class ExpenseJsonReader {
    public static List<ExpenseIncome> readExpensesFromJson(String filename) throws IOException {
        Gson gson = new Gson();
        Type expenseListType = new TypeToken<List<ExpenseIncome>>() {}.getType();

        try (Reader reader = new FileReader(filename)) {
            List<ExpenseIncome> expenses = gson.fromJson(reader, expenseListType);
            return expenses;
        } catch (IOException e) {
            throw new IOException("Error reading expenses from JSON file: " + e.getMessage());
        }
    }
}

