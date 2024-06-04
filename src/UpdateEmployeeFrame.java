import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UpdateEmployeeFrame extends JFrame {
    // Employee number to identify the employee to update
    private String employeeNumber;
    // Panel to hold employee details
    private JPanel detailsPanel;
    // Array of text fields for employee details
    private JTextField[] textFields;
    // Button to trigger the update
    private JButton updateButton;
    // Reference to the table model for updating the table
    private EmployeeTableModel tableModel;

    // Constructor
    public UpdateEmployeeFrame(String employeeNumber, EmployeeTableModel tableModel) {
        this.employeeNumber = employeeNumber;
        this.tableModel = tableModel;
        setTitle("Update Employee");
        setLayout(new BorderLayout());

        detailsPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        add(scrollPane, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Load employee details into the panel
        loadEmployeeDetails(gbc);

        // Create and add update button
        updateButton = new JButton("Update");
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        detailsPanel.add(updateButton, gbc);

        // Add action listener to update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployeeDetails();
            }
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
                    // Add labels and text fields for each employee attribute
                    addLabelAndTextField("Employee Number:", data[0], gbc, 0, false);
                    addLabelAndTextField("Last Name:", data[1], gbc, 1, true);
                    addLabelAndTextField("First Name:", data[2], gbc, 2, true);
                    addLabelAndTextField("SSS No.:", data[3], gbc, 3, true);
                    addLabelAndTextField("PhilHealth No.:", data[4], gbc, 4, true);
                    addLabelAndTextField("TIN:", data[5], gbc, 5, true);
                    addLabelAndTextField("Pagibig No.:", data[6], gbc, 6, true);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add label and text field to the panel
    private void addLabelAndTextField(String labelText, String valueText, GridBagConstraints gbc, int row, boolean editable) {
        gbc.gridx = 0;
        gbc.gridy = row;
        detailsPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        JTextField textField = new JTextField(valueText, 10);
        textField.setEditable(editable);
        detailsPanel.add(textField, gbc);

        // Store text field reference if editable
        if (editable) {
            if (textFields == null) {
                textFields = new JTextField[6];
            }
            textFields[row - 1] = textField;
        }
    }

    // Method to update employee details
    private void updateEmployeeDetails() {
        // Retrieve new details from text fields
        String[] newDetails = new String[textFields.length];
        for (int i = 0; i < textFields.length; i++) {
            newDetails[i] = textFields[i].getText();
        }
        // Update employee details in the table model
        tableModel.updateEmployeeDetails(employeeNumber, newDetails);
        // Close the frame
        dispose();
    }
}
