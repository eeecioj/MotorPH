import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {

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

    public MainFrame() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        // Create a sidebar panel for the menu
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridBagLayout());
        sidebar.setBackground(new Color(0x16326E));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Employee button to the sidebar
        JButton employeeButton = new JButton("Employee");
        gbc.gridy = 0;
        sidebar.add(employeeButton, gbc);

        // Add Payroll button to the sidebar
        JButton payrollButton = new JButton("Payroll");
        gbc.gridy = 1;
        sidebar.add(payrollButton, gbc);

        // Add Leave Request button to the sidebar
        JButton leaveRequestButton = new JButton("Leave Request");
        gbc.gridy = 2;
        sidebar.add(leaveRequestButton, gbc);

        add(sidebar, BorderLayout.WEST);

        // Main panel for employee details and buttons
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0x16326E, true)); // Set color with 30% transparency
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Employee details input panel
        JPanel detailsInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDetails = new GridBagConstraints();
        gbcDetails.insets = new Insets(5, 5, 5, 5);
        gbcDetails.fill = GridBagConstraints.HORIZONTAL;
        gbcDetails.anchor = GridBagConstraints.WEST;

        // Create employee detail labels and text fields
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

        // Add employee detail fields to the panel
        addField(detailsInputPanel, gbcDetails, lastNameLabel, lastNameField, 0, 0);
        addField(detailsInputPanel, gbcDetails, firstNameLabel, firstNameField, 0, 1);
        addField(detailsInputPanel, gbcDetails, birthdayLabel, birthdayField, 0, 2);

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

        // Create table to display employee data
        String[] columnNames = {"Employee No", "Last Name", "First Name", "Birthday", "Address", "Phone No"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        employeeTable.setPreferredScrollableViewportSize(new Dimension(500, employeeTable.getRowHeight() * 10));
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        // Use JSplitPane to split the main panel into details input and table sections
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailsInputPanel, tableScrollPane);
        splitPane.setResizeWeight(0.5); // Adjust the resize weight as needed

        mainPanel.add(splitPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Action listeners for sidebar buttons
        employeeButton.addActionListener(e -> mainPanel.setVisible(true));
        payrollButton.addActionListener(e -> {
            mainPanel.setVisible(false);
            JOptionPane.showMessageDialog(this, "Payroll management functionality is not implemented yet.");
        });
        leaveRequestButton.addActionListener(e -> {
            mainPanel.setVisible(false);
            JOptionPane.showMessageDialog(this, "Leave request functionality is not implemented yet.");
        });

        // Populate the table with employee data
        populateEmployeeTable();

        // Table selection listener to update text fields when a row is selected
        employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = employeeTable.getSelectedRow();
                    if (selectedRow != -1) {
                        populateEmployeeDetails(selectedRow);
                    }
                }
            }
        });

        // Create a button panel for create, update, delete, and clear buttons
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
        
        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

        // Button action listeners
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        createButton.addActionListener(e -> createEmployee());
        clearButton.addActionListener(e -> clearFields());

        // Set frame properties
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField field, int row, int col) {
        gbc.gridx = col;
        gbc.gridy = row * 2;
        panel.add(label, gbc);
        gbc.gridy = row * 2 + 1;
        panel.add(field, gbc);
    }
    
    // Method to clear all text fields
    private void clearFields() {
        lastNameField.setText("");
        firstNameField.setText("");
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
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    private void createEmployee() {
        String employeeNumber = JOptionPane.showInputDialog(this, "Enter Employee Number:");
        if (employeeNumber != null && !employeeNumber.trim().isEmpty()) {
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

            CreateEmployeeAction createAction = new CreateEmployeeAction();
            createAction.createEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNo, philHealthNo, tin, pagibigNo, status, position, supervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemimonthlyRate, hourlyRate);

            // Add row to table
            tableModel.addRow(new Object[]{
                employeeNumber, lastName, firstName, birthday, address, phoneNumber
            });
        } else {
            JOptionPane.showMessageDialog(this, "Employee Number is required.");
        }
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame(MainFrame::new));
    }
}






