import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTable employeeTable;          // Table to display employee data
    private EmployeeTableModel tableModel; // Custom table model for employee data

    public MainFrame() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        // Initialize the table model and table
        tableModel = new EmployeeTableModel();
        employeeTable = new JTable(tableModel);
        
        // Add the table to a scroll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around buttons
        gbc.gridx = 0;

        // Create buttons for different actions
        JButton viewButton = new JButton("View Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton createButton = new JButton("Create Employee");
        JButton leaveButton = new JButton("Request Leave");

        // Set a consistent size for all buttons
        Dimension buttonSize = new Dimension(200, 30);
        viewButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        createButton.setPreferredSize(buttonSize);
        leaveButton.setPreferredSize(buttonSize);

        // Add buttons to the panel with appropriate spacing
        gbc.gridy = 0;
        buttonPanel.add(viewButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(updateButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(deleteButton, gbc);
        gbc.gridy = 3;
        buttonPanel.add(createButton, gbc);
        gbc.gridy = 4;
        buttonPanel.add(leaveButton, gbc);

        // Add the button panel to the right side of the main frame
        add(buttonPanel, BorderLayout.EAST);

        // Add action listeners to buttons
        viewButton.addActionListener(e -> viewEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        createButton.addActionListener(e -> createEmployee());
        leaveButton.addActionListener(e -> requestLeave());

        // Set frame properties
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true); // Make the frame visible
    }

    // Method to view employee details
    private void viewEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new EmployeeDetailsFrame(employeeNumber).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to view.");
        }
    }

    // Method to update employee details
    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new UpdateEmployeeFrame(employeeNumber, tableModel).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    // Method to delete an employee
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new DeleteEmployeeFrame(employeeNumber, tableModel).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    // Method to create a new employee
    private void createEmployee() {
        new CreateEmployeeFrame(tableModel).setVisible(true);
    }

    // Method to request leave for an employee
    private void requestLeave() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            String employeeName = (String) tableModel.getValueAt(selectedRow, 1); // Assuming employee name is in the second column
            new LeaveApplicationFrame(employeeNumber, employeeName).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to apply leave.");
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame(MainFrame::new));
    }
}
