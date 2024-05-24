import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    private String[] columnNames = {"Employee Number", "Last Name", "First Name", "SSS No.", "PhilHealth No.", "TIN", "Pagibig No."};
    private List<String[]> data;

    public EmployeeTableModel() {
        loadEmployeeData();
    }

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

    public void deleteEmployee(String employeeNumber) {
        data.removeIf(row -> row[0].equals(employeeNumber));
        saveEmployeeData();
        fireTableDataChanged();
    }

    private void saveEmployeeData() {
        String csvFile = "employees.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            // Write the header line
            bw.write(String.join(",", columnNames));
            bw.newLine();
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
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    public String[] getEmployeeDetails(String employeeNumber) {
        for (String[] employee : data) {
            if (employee[0].equals(employeeNumber)) {
                return employee;
            }
        }
        return null; // Employee not found
    }

    public void updateEmployeeDetails(String employeeNumber, String[] newDetails) {
        for (String[] employee : data) {
            if (employee[0].equals(employeeNumber)) {
                for (int i = 0; i < newDetails.length; i++) {
                    employee[i + 1] = newDetails[i]; // Update fields, excluding employee number
                }
                saveEmployeeData();
                fireTableDataChanged();
                return;
            }
        }
        // Employee not found, handle accordingly
    }

    void addEmployee(String[] string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
