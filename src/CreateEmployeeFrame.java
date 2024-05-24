import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateEmployeeFrame extends JFrame {
    private JTextField employeeNumberField, lastNameField, firstNameField, sssNoField, philHealthNoField, tinField, pagibigNoField;
    private JButton createButton;
    private EmployeeTableModel tableModel;

    public CreateEmployeeFrame(EmployeeTableModel tableModel) {
        this.tableModel = tableModel;
        setTitle("Create New Employee");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(employeeNumberLabel, gbc);

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

        createButton = new JButton("Create");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(createButton, gbc);

        createButton.addActionListener(e -> createEmployee());

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createEmployee() {
        String employeeNumber = employeeNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String sssNo = sssNoField.getText();
        String philHealthNo = philHealthNoField.getText();
        String tin = tinField.getText();
        String pagibigNo = pagibigNoField.getText();

        if (employeeNumber.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || sssNo.isEmpty() || philHealthNo.isEmpty() || tin.isEmpty() || pagibigNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.csv", true))) {
            writer.write(employeeNumber + "," + lastName + "," + firstName + "," + sssNo + "," + philHealthNo + "," + tin + "," + pagibigNo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving employee data.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addEmployee(new String[]{employeeNumber, lastName, firstName, sssNo, philHealthNo, tin, pagibigNo});
        JOptionPane.showMessageDialog(this, "Employee created successfully.");
        dispose();
    }
}
