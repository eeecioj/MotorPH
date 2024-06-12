
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

class CreateEmployeeAction {
    public void createEmployee(String employeeNumber, String lastName, String firstName, String birthday, String address, String phoneNumber,
                               String sssNo, String philHealthNo, String tin, String pagibigNo, String status, String position,
                               String supervisor, String basicSalary, String riceSubsidy, String phoneAllowance, String clothingAllowance,
                               String grossSemimonthlyRate, String hourlyRate) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("employee.csv", true))) {
            bw.write(String.join(";", employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNo, philHealthNo, tin,
                    pagibigNo, status, position, supervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance,
                    grossSemimonthlyRate, hourlyRate));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}