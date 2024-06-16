
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
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data[0].equals(employeeNumber)) {
                    // Update the line with the new data
                    line = String.join(";", employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNo, philHealthNo, tin, pagibigNo, status, position, supervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemimonthlyRate, hourlyRate);
                }
                // Write the line to the temporary file
                bw.write(line);
                bw.newLine();
            }

            // Close the input and output streams
            br.close();
            bw.close();

            // Delete the original file
            inputFile.delete();

            // Rename the temporary file to the original file
            tempFile.renameTo(inputFile);

            JOptionPane.showMessageDialog(null, "Employee updated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating employee.");
        }
    }
}