/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author MrRight
 */
package com.mycompany.expense_manager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ExpenseJsonWriter {

    public static void writeExpensesToJson(List<ExpenseIncome> expenses, String filename) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Read existing JSON data from file, if any
        List<ExpenseIncome> existingExpenses = readExpensesFromJson(filename);

        // If existingExpenses is null, initialize it with an empty list
        if (existingExpenses == null) {
            existingExpenses = new ArrayList<>();
        }

        // Append new expenses to the existing list
        existingExpenses.addAll(expenses);

        // Convert the combined list to JSON
        String json = gson.toJson(existingExpenses);

        // Write JSON data to file
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        }
    }

    public static List<ExpenseIncome> readExpensesFromJson(String filename) throws IOException {
        Gson gson = new Gson();

        // Check if file exists
        if (!Files.exists(Path.of(filename))) {
            // Return an empty list if the file doesn't exist
            return new ArrayList<>();
        }

        // Read existing JSON data from file
        String jsonData = new String(Files.readAllBytes(Path.of(filename)));

        // Parse JSON data into list of ExpenseIncome objects
        return gson.fromJson(jsonData, new TypeToken<List<ExpenseIncome>>() {}.getType());
    }

    // Example usage
   
}
