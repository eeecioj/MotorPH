import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeePanel extends JPanel {

    private JTextField employeeNumberField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField birthdayField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField sssNoField;
    private JTextField philHealthNoField;
    private JTextField tinField;
    private JTextField pagibigNoField;
    private JTextField statusField;
    private JTextField positionField;
    private JTextField supervisorField;
    private JTextField basicSalaryField;
    private JTextField riceSubsidyField;
    private JTextField phoneAllowanceField;
    private JTextField clothingAllowanceField;
    private JTextField grossSemimonthlyRateField;
    private JTextField hourlyRateField;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    
    private void updateDataFromFields() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            // Retrieve data from text fields
            String employeeNumber = employeeNumberField.getText();
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            String birthday = birthdayField.getText();
            String address = addressField.getText();
            String phoneNumber = phoneNumberField.getText();

            // Update the row in the table model
            tableModel.setValueAt(employeeNumber, selectedRow, 0);
            tableModel.setValueAt(lastName, selectedRow, 1);
            tableModel.setValueAt(firstName, selectedRow, 2);
            tableModel.setValueAt(birthday, selectedRow, 3);
            tableModel.setValueAt(address, selectedRow, 4);
            tableModel.setValueAt(phoneNumber, selectedRow, 5);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
        }
    }

    public EmployeePanel() {
        setLayout(new BorderLayout());

        JPanel detailsInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDetails = new GridBagConstraints();
        gbcDetails.insets = new Insets(5, 5, 5, 5);
        gbcDetails.fill = GridBagConstraints.HORIZONTAL;
        gbcDetails.anchor = GridBagConstraints.WEST;

        JLabel employeeNumberLabel = new JLabel("Employee No:");
        employeeNumberField = new JTextField(15);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);
        JLabel birthdayLabel = new JLabel("Birthday:");
        birthdayField = new JTextField(15);
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(15);
        JLabel phoneNumberLabel = new JLabel("Phone No:");
        phoneNumberField = new JTextField(15);
        JLabel sssNoLabel = new JLabel("SSS No:");
        sssNoField = new JTextField(15);
        JLabel philHealthNoLabel = new JLabel("PhilHealth No:");
        philHealthNoField = new JTextField(15);
        JLabel tinLabel = new JLabel("TIN:");
        tinField = new JTextField(15);
        JLabel pagibigNoLabel = new JLabel("Pagibig No:");
        pagibigNoField = new JTextField(15);
        JLabel statusLabel = new JLabel("Status:");
        statusField = new JTextField(15);
        JLabel positionLabel = new JLabel("Position:");
        positionField = new JTextField(15);
        JLabel supervisorLabel = new JLabel("Immediate Supervisor:");
        supervisorField = new JTextField(15);
        JLabel basicSalaryLabel = new JLabel("Basic Salary:");
        basicSalaryField = new JTextField(15);
        JLabel riceSubsidyLabel = new JLabel("Rice Subsidy:");
        riceSubsidyField = new JTextField(15);
        JLabel phoneAllowanceLabel = new JLabel("Phone Allowance:");
        phoneAllowanceField = new JTextField(15);
        JLabel clothingAllowanceLabel = new JLabel("Clothing Allowance:");
        clothingAllowanceField = new JTextField(15);
        JLabel grossSemimonthlyRateLabel = new JLabel("Gross Semi-monthly Rate:");
        grossSemimonthlyRateField = new JTextField(15);
        JLabel hourlyRateLabel = new JLabel("Hourly Rate:");
        hourlyRateField = new JTextField(15);

        addField(detailsInputPanel, gbcDetails, employeeNumberLabel, employeeNumberField, 0, 0);
        addField(detailsInputPanel, gbcDetails, lastNameLabel, lastNameField, 0, 1);
        addField(detailsInputPanel, gbcDetails, firstNameLabel, firstNameField, 0, 2);
        addField(detailsInputPanel, gbcDetails, birthdayLabel, birthdayField, 0, 3);
        addField(detailsInputPanel, gbcDetails, addressLabel, addressField, 1, 0);
        addField(detailsInputPanel, gbcDetails, phoneNumberLabel, phoneNumberField, 1, 1);
        addField(detailsInputPanel, gbcDetails, sssNoLabel, sssNoField, 1, 2);
        addField(detailsInputPanel, gbcDetails, philHealthNoLabel, philHealthNoField, 1, 3);
        addField(detailsInputPanel, gbcDetails, tinLabel, tinField, 2, 0);
        addField(detailsInputPanel, gbcDetails, pagibigNoLabel, pagibigNoField, 2, 1);
        addField(detailsInputPanel, gbcDetails, statusLabel, statusField, 2, 2);
        addField(detailsInputPanel, gbcDetails, positionLabel, positionField, 2, 3);
        addField(detailsInputPanel, gbcDetails, supervisorLabel, supervisorField, 3, 0);
        addField(detailsInputPanel, gbcDetails, riceSubsidyLabel, riceSubsidyField, 3, 1);
        addField(detailsInputPanel, gbcDetails, phoneAllowanceLabel, phoneAllowanceField, 3, 2);
        addField(detailsInputPanel, gbcDetails, clothingAllowanceLabel, clothingAllowanceField, 3, 3);
        addField(detailsInputPanel, gbcDetails, basicSalaryLabel, basicSalaryField, 4, 0);
        addField(detailsInputPanel, gbcDetails, grossSemimonthlyRateLabel, grossSemimonthlyRateField, 4, 1);
        addField(detailsInputPanel, gbcDetails, hourlyRateLabel, hourlyRateField, 4, 2);

        String[] columnNames = {"Employee No", "Last Name", "First Name", "Birthday", "Address", "Phone No"};
        tableModel = new NonEditableTableModel();
        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }
        employeeTable = new JTable(tableModel);
        employeeTable.setPreferredScrollableViewportSize(new Dimension(500, employeeTable.getRowHeight() * 10));
        
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailsInputPanel, tableScrollPane);
        splitPane.setResizeWeight(0.5);

        add(splitPane, BorderLayout.CENTER);

        populateEmployeeTable();
        
        ListSelectionModel selectionModel = employeeTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure this is not a transient change
                    int selectedRow = employeeTable.getSelectedRow();
                    if (selectedRow != -1) {
                        populateEmployeeDetails(selectedRow);
                    }
                }
            }
        });


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.insets = new Insets(5, 5, 5, 5);
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;
        gbcButtons.anchor = GridBagConstraints.CENTER;

        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton createButton = new JButton("Create");
        JButton clearButton = new JButton("Clear");
        
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        buttonPanel.add(clearButton, gbcButtons);

        gbcButtons.gridx = 1;
        buttonPanel.add(updateButton, gbcButtons);

        gbcButtons.gridx = 2;
        buttonPanel.add(deleteButton, gbcButtons);

        gbcButtons.gridx = 3;
        buttonPanel.add(createButton, gbcButtons);
        
        // Add the button panel to the details input panel
        gbcDetails.gridx = 0;
        gbcDetails.gridy = 10;
        gbcDetails.gridwidth = 4; // Span across the whole panel
        gbcDetails.anchor = GridBagConstraints.CENTER;
        detailsInputPanel.add(buttonPanel, gbcDetails);

        // Button action listeners
        updateButton.addActionListener(e -> {
            // Update the data of the selected row
            updateDataFromFields();
            // Clear fields
            clearFields();
        });
        deleteButton.addActionListener(e -> deleteEmployee());
        createButton.addActionListener(e -> createEmployee());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField field, int row, int col) {
        gbc.gridx = col;
        gbc.gridy = row * 2;
        panel.add(label, gbc);
        gbc.gridy = row * 2 + 1;
        panel.add(field, gbc);
    }

    private void populateEmployeeTable() {
        try (BufferedReader br = new BufferedReader(new FileReader("employee.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 6) {
                    tableModel.addRow(new Object[]{
                        data[0].trim(), data[1].trim(), data[2].trim(),
                        data[3].trim(), data[4].trim(), data[5].trim()
                    });
                } else {
                    System.out.println("Skipping line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateEmployeeDetails(int selectedRow) {
        String employeeNumber = tableModel.getValueAt(selectedRow, 0).toString();
        try (BufferedReader br = new BufferedReader(new FileReader("employee.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data[0].trim().equals(employeeNumber)) {
                    employeeNumberField.setText(data[0].trim());
                    lastNameField.setText(data[1].trim());
                    firstNameField.setText(data[2].trim());
                    birthdayField.setText(data[3].trim());
                    addressField.setText(data[4].trim());
                    phoneNumberField.setText(data[5].trim());
                    sssNoField.setText(data[6].trim());
                    philHealthNoField.setText(data[7].trim());
                    tinField.setText(data[8].trim());
                    pagibigNoField.setText(data[9].trim());
                    statusField.setText(data[10].trim());
                    positionField.setText(data[11].trim());
                    supervisorField.setText(data[12].trim());
                    basicSalaryField.setText(data[13].trim());
                    riceSubsidyField.setText(data[14].trim());
                    phoneAllowanceField.setText(data[15].trim());
                    clothingAllowanceField.setText(data[16].trim());
                    grossSemimonthlyRateField.setText(data[17].trim());
                    hourlyRateField.setText(data[18].trim());
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            String employeeNumber = tableModel.getValueAt(selectedRow, 0).toString();
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            String birthday = birthdayField.getText();
            String address = addressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String sssNo = sssNoField.getText();
            String philHealthNo = philHealthNoField.getText();
            String tin = tinField.getText();
            String pagibigNo = pagibigNoField.getText();
            String status = statusField.getText();
            String position = positionField.getText();
            String supervisor = supervisorField.getText();
            String basicSalary = basicSalaryField.getText();
            String riceSubsidy = riceSubsidyField.getText();
            String phoneAllowance = phoneAllowanceField.getText();
            String clothingAllowance = clothingAllowanceField.getText();
            String grossSemimonthlyRate = grossSemimonthlyRateField.getText();
            String hourlyRate = hourlyRateField.getText();

            UpdateEmployeeAction updateAction = new UpdateEmployeeAction();
            updateAction.updateEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNo, philHealthNo, tin, pagibigNo, status, position, supervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemimonthlyRate, hourlyRate);

            // Update table
            tableModel.setValueAt(lastName, selectedRow, 1);
            tableModel.setValueAt(firstName, selectedRow, 2);
            tableModel.setValueAt(birthday, selectedRow, 3);
            tableModel.setValueAt(address, selectedRow, 4);
            tableModel.setValueAt(phoneNumber, selectedRow, 5);
            
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }
    
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            String employeeNumber = tableModel.getValueAt(selectedRow, 0).toString();
            DeleteEmployeeAction deleteAction = new DeleteEmployeeAction();
            deleteAction.deleteEmployee(employeeNumber);

            // Remove row from table
            tableModel.removeRow(selectedRow);
            
            // Clear fields
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    private void createEmployee() {
        // Check if a row is selected in the table
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            JOptionPane.showMessageDialog(this, "Please clear the current selection to create a new employee.");
            return;
        }

        // Retrieve data from text fields
        String employeeNumber = employeeNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String birthday = birthdayField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String sssNo = sssNoField.getText();
        String philHealthNo = philHealthNoField.getText();
        String tin = tinField.getText();
        String pagibigNo = pagibigNoField.getText();
        String status = statusField.getText();
        String position = positionField.getText();
        String supervisor = supervisorField.getText();
        String basicSalary = basicSalaryField.getText();
        String riceSubsidy = riceSubsidyField.getText();
        String phoneAllowance = phoneAllowanceField.getText();
        String clothingAllowance = clothingAllowanceField.getText();
        String grossSemimonthlyRate = grossSemimonthlyRateField.getText();
        String hourlyRate = hourlyRateField.getText();

        // Validate if all required fields are filled
        if (lastNameField.getText().isEmpty() || firstNameField.getText().isEmpty() || birthdayField.getText().isEmpty() ||
            addressField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || sssNoField.getText().isEmpty() ||
            philHealthNoField.getText().isEmpty() || tinField.getText().isEmpty() || pagibigNoField.getText().isEmpty() ||
            statusField.getText().isEmpty() || positionField.getText().isEmpty() || supervisorField.getText().isEmpty() ||
            basicSalaryField.getText().isEmpty() || riceSubsidyField.getText().isEmpty() || phoneAllowanceField.getText().isEmpty() ||
            clothingAllowanceField.getText().isEmpty() || grossSemimonthlyRateField.getText().isEmpty() || hourlyRateField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        return;
    }
        
        // Add data to table
        tableModel.addRow(new Object[]{
                employeeNumber, lastName, firstName, birthday, address, phoneNumber
        });
        
        // Select the newly added row
        int newRow = tableModel.getRowCount() - 1;
        employeeTable.setRowSelectionInterval(newRow, newRow);

        // CreateEmployeeAction instance
        CreateEmployeeAction createEmployeeAction = new CreateEmployeeAction();

        // Call createEmployee method
        createEmployeeAction.createEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNo, philHealthNo, tin,
                pagibigNo, status, position, supervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance,
                grossSemimonthlyRate, hourlyRate);

        // Show success message
        JOptionPane.showMessageDialog(this, "Employee added successfully.");
        
        // Clear fields
        clearFields();
    }

    private void clearFields() {
        employeeTable.clearSelection();
        employeeNumberField.setText("");
        lastNameField.setText("");
        firstNameField.setText("");
        birthdayField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
        sssNoField.setText("");
        philHealthNoField.setText("");
        tinField.setText("");
        pagibigNoField.setText("");
        statusField.setText("");
        positionField.setText("");
        supervisorField.setText("");
        basicSalaryField.setText("");
        riceSubsidyField.setText("");
        phoneAllowanceField.setText("");
        clothingAllowanceField.setText("");
        grossSemimonthlyRateField.setText("");
        hourlyRateField.setText("");
    }

    private class NonEditableTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        EmployeePanel employeePanel = new EmployeePanel();
        frame.add(employeePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}