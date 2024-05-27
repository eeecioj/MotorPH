import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTable employeeTable;
    private EmployeeTableModel tableModel;

    public MainFrame() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        tableModel = new EmployeeTableModel();
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;

        JButton viewButton = new JButton("View Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton createButton = new JButton("Create Employee");
        JButton leaveButton = new JButton("Apply Leave");

        Dimension buttonSize = new Dimension(200, 30);
        viewButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        createButton.setPreferredSize(buttonSize);
        leaveButton.setPreferredSize(buttonSize);

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

        add(buttonPanel, BorderLayout.EAST);

        viewButton.addActionListener(e -> viewEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        createButton.addActionListener(e -> createEmployee());
        leaveButton.addActionListener(e -> applyLeave());

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void viewEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new EmployeeDetailsFrame(employeeNumber).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to view.");
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new UpdateEmployeeFrame(employeeNumber, tableModel).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new DeleteEmployeeFrame(employeeNumber, tableModel).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    private void createEmployee() {
        new CreateEmployeeFrame(tableModel).setVisible(true);
    }

    private void applyLeave() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            String employeeName = (String) tableModel.getValueAt(selectedRow, 1); // Assuming employee name is in the second column
            new LeaveApplicationFrame(employeeNumber, employeeName).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to apply leave.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame(MainFrame::new));
    }
}
