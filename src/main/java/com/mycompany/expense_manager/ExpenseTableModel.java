/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.expense_manager;

import java.io.File;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.io.IOException;

public class ExpenseTableModel extends DefaultTableModel {
    public void populateTableFromJson() {
        // Check if the file exists
        File file = new File("expenses.json");
        if (!file.exists()) {
            // Handle the case where the file does not exist
            System.out.println("File does not exist.");
            return; // or any appropriate action
        }

        // Clear existing data from the table
        setRowCount(0); // Assuming the table model extends DefaultTableModel

        try {
            // Read expenses from JSON file
            List<ExpenseIncome> expenses = ExpenseJsonReader.readExpensesFromJson("expenses.json");

            // Populate table with expense data
            for (ExpenseIncome expense : expenses) {
                Object[] rowData = {
                    expense.getDate(),
                    expense.getType(),
                    expense.getTitle(),
                    expense.getAmount()
                };
                addRow(rowData);
            }
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
    }
}