import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DeleteEmployeeFrame extends JFrame {
    // Employee number to identify the employee to delete
    private String employeeNumber;
    // Panel to hold employee details
    private JPanel detailsPanel;
    // Button to trigger the deletion
    private JButton deleteButton;
    // Reference to the table model for updating the table
    private EmployeeTableModel tableModel;

    // Constructor
    public DeleteEmployeeFrame(String employeeNumber, EmployeeTableModel tableModel) {
        this.employeeNumber = employeeNumber;
        this.tableModel = tableModel;
        setTitle("Delete Employee"); // Set frame title
        setLayout(new BorderLayout()); // Set layout to BorderLayout

        detailsPanel = new JPanel(new GridBagLayout()); // Initialize panel with GridBagLayout
        JScrollPane scrollPane = new JScrollPane(detailsPanel); // Create scroll pane for the panel
        add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center of the frame

        GridBagConstraints gbc = new GridBagConstraints(); // Initialize GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets
        gbc.anchor = GridBagConstraints.WEST;

        // Load employee details into the panel
        loadEmployeeDetails(gbc);

        // Create and add delete button
        deleteButton = new JButton("Delete");
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        detailsPanel.add(deleteButton, gbc);

        // Add action listener to delete button
        deleteButton.addActionListener(e -> {
            tableModel.deleteEmployee(employeeNumber); // Delete employee from the table model
            dispose(); // Close the frame
        });

        setSize(500, 350); // Set frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to load employee details from CSV file
    private void loadEmployeeDetails(GridBagConstraints gbc) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(employeeNumber)) {
                    // Add labels and values for each employee attribute
                    addLabelAndValue("Employee Number:", data[0], gbc, 0);
                    addLabelAndValue("Last Name:", data[1], gbc, 1);
                    addLabelAndValue("First Name:", data[2], gbc, 2);
                    addLabelAndValue("SSS No.:", data[3], gbc, 3);
                    addLabelAndValue("PhilHealth No.:", data[4], gbc, 4);
                    addLabelAndValue("TIN:", data[5], gbc, 5);
                    addLabelAndValue("Pagibig No.:", data[6], gbc, 6);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add label and value to the panel
    private void addLabelAndValue(String labelText, String valueText, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        detailsPanel.add(new JLabel(labelText), gbc); // Add label to panel
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(valueText), gbc); // Add value to panel
    }
}
