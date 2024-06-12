
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

class DeleteEmployeeAction {

    public void deleteEmployee(String employeeNumber) {
        File inputFile = new File("employee.csv");
        File tempFile = new File("employee_temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (!data[0].equals(employeeNumber)) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Employee not found.");
            } else {
                JOptionPane.showMessageDialog(null, "Employee deleted successfully.");
            }

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting employee.");
        }
    }
}