
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

class UpdateEmployeeAction {

    public void updateEmployee(String employeeNumber, String lastName, String firstName, String birthday, String address, String phoneNumber, String sssNo, String philHealthNo, String tin, String pagibigNo, String status, String position, String supervisor, String basicSalary, String riceSubsidy, String phoneAllowance, String clothingAllowance, String grossSemimonthlyRate, String hourlyRate) {
        File inputFile = new File("employee.csv");
        File tempFile = new File("employee_temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data[0].equals(employeeNumber)) {
                    bw.write(String.join(";", employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNo, philHealthNo, tin, pagibigNo, status, position, supervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemimonthlyRate, hourlyRate));
                    bw.newLine();
                    found = true;
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Employee not found.");
            } else {
                JOptionPane.showMessageDialog(null, "Employee updated successfully.");
            }

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating employee.");
        }
    }
}