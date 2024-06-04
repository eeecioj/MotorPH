import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    // Array to hold column names for the table
    private String[] columnNames = {"Employee Number", "Last Name", "First Name", "SSS No.", "PhilHealth No.", "TIN", "Pagibig No."};
    
    // List to hold the data for the table
    private List<String[]> data;

    // Constructor to initialize the table model by loading data from a CSV file
    public EmployeeTableModel() {
        loadEmployeeData();
    }

    // Method to load employee data from a CSV file
    private void loadEmployeeData() {
        data = new ArrayList<>();
        String csvFile = "employees.csv";
        String line;
        String csvSeparator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(csvSeparator);
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an employee from the table
    public void deleteEmployee(String employeeNumber) {
        data.removeIf(row -> row[0].equals(employeeNumber)); // Remove employee if its number matches the provided number
        saveEmployeeData(); // Save updated data to the CSV file
        fireTableDataChanged(); // Notify table that the data has changed
    }

    // Method to save the employee data to a CSV file
    private void saveEmployeeData() {
        String csvFile = "employees.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            // Write the header line
            bw.write(String.join(",", columnNames));
            bw.newLine();
            // Write each row of data
            for (String[] row : data) {
                StringBuilder rowString = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    rowString.append(row[i]);
                    if (i < row.length - 1) {
                        rowString.append(",");
                    }
                }
                bw.write(rowString.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.size(); // Return the number of rows in the table
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Return the number of columns in the table
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; // Return the name of the specified column
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex]; // Return the value at the specified row and column
    }

    // Method to get the details of an employee with a given employee number
    public String[] getEmployeeDetails(String employeeNumber) {
        for (String[] employee : data) {
            if (employee[0].equals(employeeNumber)) {
                return employee; // Return the details of the employee
            }
        }
        return null; // Return null if employee not found
    }

    // Method to update the details of an employee
    public void updateEmployeeDetails(String employeeNumber, String[] newDetails) {
        for (String[] employee : data) {
            if (employee[0].equals(employeeNumber)) {
                for (int i = 0; i < newDetails.length; i++) {
                    employee[i + 1] = newDetails[i]; // Update fields, excluding employee number
                }
                saveEmployeeData(); // Save updated data to the CSV file
                fireTableDataChanged(); // Notify table that the data has changed
                return;
            }
        }
    }
    
    // Method to add a new employee to the table
    public void addEmployee(String[] employeeDetails) {
        data.add(employeeDetails); // Add new employee details to the data list
        saveEmployeeData(); // Save updated data to the CSV file
        fireTableDataChanged(); // Notify table that the data has changed
    }
}
