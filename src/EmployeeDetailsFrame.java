import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDetailsFrame extends JFrame {
    private String employeeNumber;            // Store the employee number
    private JPanel detailsPanel;              // Panel to hold employee details
    private JComboBox<String> monthComboBox;  // ComboBox to select month
    private JButton computeButton;            // Button to compute salary
    private JTextArea payDetailsArea;         // Area to display pay details

    public EmployeeDetailsFrame(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        setTitle("Employee Details");
        setLayout(new BorderLayout());

        detailsPanel = new JPanel(new GridBagLayout());  // Panel to hold employee details
        JScrollPane scrollPane = new JScrollPane(detailsPanel);  // Scroll pane for details panel
        add(scrollPane, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        loadEmployeeDetails(gbc);  // Load employee details from CSV file

        JLabel monthLabel = new JLabel("Select Month:");
        gbc.gridx = 0;
        gbc.gridy += 1;
        detailsPanel.add(monthLabel, gbc);

        // ComboBox to select month
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        gbc.gridx = 1;
        detailsPanel.add(monthComboBox, gbc);

        // Button to compute salary
        computeButton = new JButton("Compute");
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        detailsPanel.add(computeButton, gbc);

        // Area to display pay details
        payDetailsArea = new JTextArea(10, 40);
        payDetailsArea.setEditable(false);
        JScrollPane payDetailsScrollPane = new JScrollPane(payDetailsArea);
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        detailsPanel.add(payDetailsScrollPane, gbc);

        // Action listener for compute button
        computeButton.addActionListener(e -> computeSalary((String) monthComboBox.getSelectedItem()));

        setSize(500, 450);  // Set frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Dispose frame on close
        setLocationRelativeTo(null);  // Center the frame on screen
    }

    // Method to load employee details from CSV file
    private void loadEmployeeDetails(GridBagConstraints gbc) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(employeeNumber)) {
                    // Add labels and values to display employee details
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

    // Method to add label and value to the details panel
    private void addLabelAndValue(String labelText, String valueText, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        detailsPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        detailsPanel.add(new JLabel(valueText), gbc);
    }

    // Method to compute salary for the selected month
    private void computeSalary(String month) {
        StringBuilder payDetails = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("salary.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(employeeNumber) && data[1].equals(month)) {
                    // Append pay details for the selected month
                    payDetails.append("Month: ").append(data[1]).append("\n")
                              .append("Basic Salary: ").append(data[2]).append("\n")
                              .append("Deductions: ").append(data[3]).append("\n")
                              .append("Net Pay: ").append(data[4]).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set pay details in the text area or display message if no details found
        payDetailsArea.setText(payDetails.length() > 0 ? payDetails.toString() : "No pay details found for the selected month.");
    }
}
