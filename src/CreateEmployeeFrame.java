import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateEmployeeFrame extends JFrame {
    // Fields for user input
    private JTextField employeeNumberField, lastNameField, firstNameField, sssNoField, philHealthNoField, tinField, pagibigNoField;
    // Button to create employee
    private JButton createButton;
    // Reference to the table model for updating the table
    private EmployeeTableModel tableModel;

    // Constructor
    public CreateEmployeeFrame(EmployeeTableModel tableModel) {
        this.tableModel = tableModel;
        setTitle("Create New Employee");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels and text fields for each employee attribute
        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(employeeNumberLabel, gbc);
        employeeNumberField = new JTextField(15);
        gbc.gridx = 1;
        add(employeeNumberField, gbc);

        employeeNumberField = new JTextField(15);
        gbc.gridx = 1;
        add(employeeNumberField, gbc);

        JLabel lastNameLabel = new JLabel("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lastNameLabel, gbc);

        lastNameField = new JTextField(15);
        gbc.gridx = 1;
        add(lastNameField, gbc);

        JLabel firstNameLabel = new JLabel("First Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(firstNameLabel, gbc);

        firstNameField = new JTextField(15);
        gbc.gridx = 1;
        add(firstNameField, gbc);

        JLabel sssNoLabel = new JLabel("SSS No.:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(sssNoLabel, gbc);

        sssNoField = new JTextField(15);
        gbc.gridx = 1;
        add(sssNoField, gbc);

        JLabel philHealthNoLabel = new JLabel("PhilHealth No.:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(philHealthNoLabel, gbc);

        philHealthNoField = new JTextField(15);
        gbc.gridx = 1;
        add(philHealthNoField, gbc);

        JLabel tinLabel = new JLabel("TIN:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(tinLabel, gbc);

        tinField = new JTextField(15);
        gbc.gridx = 1;
        add(tinField, gbc);

        JLabel pagibigNoLabel = new JLabel("Pagibig No.:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(pagibigNoLabel, gbc);

        pagibigNoField = new JTextField(15);
        gbc.gridx = 1;
        add(pagibigNoField, gbc);

        // Button to create employee
        createButton = new JButton("Create");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(createButton, gbc);

        // Action listener for create button
        createButton.addActionListener(e -> createEmployee());

        setSize(400, 400); // Set frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to create an employee
    private void createEmployee() {
        // Retrieve values from text fields
        String employeeNumber = employeeNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String sssNo = sssNoField.getText();
        String philHealthNo = philHealthNoField.getText();
        String tin = tinField.getText();
        String pagibigNo = pagibigNoField.getText();

        // Check if any field is empty
        if (employeeNumber.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || sssNo.isEmpty() || philHealthNo.isEmpty() || tin.isEmpty() || pagibigNo.isEmpty()) {
            // Display an error message if any field is empty
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.csv", true))) {
            // Write employee data to CSV file
            writer.write(employeeNumber + "," + lastName + "," + firstName + "," + sssNo + "," + philHealthNo + "," + tin + "," + pagibigNo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Display error message if there's an issue saving the data
            JOptionPane.showMessageDialog(this, "Error saving employee data.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add the new employee to the table model
        tableModel.addEmployee(new String[]{employeeNumber, lastName, firstName, sssNo, philHealthNo, tin, pagibigNo});

        // Display a success message
        JOptionPane.showMessageDialog(this, "Employee created successfully.");

        // Close the frame
        dispose();
    }
}
