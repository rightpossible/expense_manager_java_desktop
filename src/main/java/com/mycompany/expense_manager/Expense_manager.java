package com.mycompany.expense_manager;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Expense_manager {

    public static void main(String[] args) {
        // Check if the file exists, if not, create it
        String filename = "expenses.json";
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created new file: " + filename);

                // Write an empty JSON array to the file
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.print("[]");
                }

            } catch (IOException e) {
                System.err.println("Error creating new file: " + e.getMessage());
            }
        }

        // Initialize expenses list
        List<ExpenseIncome> expenses = new ArrayList<>();

        // Read data from JSON file
        try {
            expenses = ExpenseJsonReader.readExpensesFromJson(filename);
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
            return; // Stop further execution if file reading fails
        }

        // Create expense form with loaded data
        final List<ExpenseIncome> finalExpenses = expenses; // effectively final variable
        SwingUtilities.invokeLater(() -> {
            expense_form expenseForm = new expense_form(finalExpenses);

            // Set default close operation for the JFrame
            expenseForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Make the expense_form visible
            expenseForm.setVisible(true);
        });
    }
}
