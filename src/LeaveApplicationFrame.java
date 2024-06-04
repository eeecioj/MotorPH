import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;

public class LeaveApplicationFrame extends JFrame {
    private String employeeNumber;
    private String employeeName;
    private JLabel employeeInfoLabel;
    private JLabel employeeIdLabel;
    private JComboBox<String> leaveReasonComboBox;
    private JLabel fromDateLabel;
    private JLabel toDateLabel;
    private JXDatePicker fromDatePicker;
    private JXDatePicker toDatePicker;
    private JButton requestLeaveButton;

    // Constructor
    public LeaveApplicationFrame(String employeeNumber, String employeeName) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        setTitle("Leave Application"); // Set frame title
        setLayout(new GridBagLayout()); // Set layout to GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints(); // Initialize GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets
        gbc.anchor = GridBagConstraints.WEST;

        // Employee Info Label
        employeeInfoLabel = new JLabel("Employee Name: " + employeeName); // Create employee info label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(employeeInfoLabel, gbc); // Add employee info label to the frame

        // Employee ID Label
        employeeIdLabel = new JLabel("Employee ID: " + employeeNumber); // Create employee ID label
        gbc.gridy = 1;
        add(employeeIdLabel, gbc); // Add employee ID label to the frame

        // Leave Reason ComboBox
        JLabel leaveReasonLabel = new JLabel("Reason for Leave:"); // Create label for leave reason
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(leaveReasonLabel, gbc); // Add leave reason label to the frame

        // Leave Reason Combo Box
        String[] leaveReasons = {"Casual Leave", "Bereavement Leave", "Maternity Leave", "Paternity Leave", "Sick Leave" };
        leaveReasonComboBox = new JComboBox<>(leaveReasons); // Create combo box for leave reasons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(leaveReasonComboBox, gbc); // Add leave reason combo box to the frame

        // Date From Label and Picker
        fromDateLabel = new JLabel("Date From:"); // Create label for "Date From"
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(fromDateLabel, gbc); // Add "Date From" label to the frame

        fromDatePicker = new JXDatePicker(); // Create date picker for "Date From"
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        add(fromDatePicker, gbc); // Add date picker to the frame

        // Date To Label and Picker
        toDateLabel = new JLabel("Date To:"); // Create label for "Date To"
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(toDateLabel, gbc); // Add "Date To" label to the frame

        toDatePicker = new JXDatePicker(); // Create date picker for "Date To"
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        add(toDatePicker, gbc); // Add date picker to the frame

        // Request Leave Button
        requestLeaveButton = new JButton("Request Leave"); // Create "Request Leave" button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(requestLeaveButton, gbc); // Add "Request Leave" button to the frame

        // Add action listener to "Request Leave" button
        requestLeaveButton.addActionListener(e -> requestLeave());

        setSize(400, 300); // Set frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to handle leave request
    private void requestLeave() {
        String leaveReason = (String) leaveReasonComboBox.getSelectedItem(); // Get selected leave reason
        Date fromDate = fromDatePicker.getDate(); // Get selected "Date From"
        Date toDate = toDatePicker.getDate(); // Get selected "Date To"
        
        // Validate if both dates are selected
        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(this, "Please select the leave duration.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate if "Date To" is after "Date From"
        if (toDate.before(fromDate)) {
            JOptionPane.showMessageDialog(this, "The 'Date To' must be after 'Date From'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Format the dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateString = sdf.format(fromDate); // Format "Date From"
        String toDateString = sdf.format(toDate); // Format "Date To"

        // Write leave application details to CSV file
        try (FileWriter writer = new FileWriter("leaveapplication.csv", true)) {
                        // Append leave application details to the CSV file
            writer.append(employeeNumber)
                  .append(',')
                  .append(employeeName)
                  .append(',')
                  .append(leaveReason)
                  .append(',')
                  .append(fromDateString)
                  .append(',')
                  .append(toDateString)
                  .append('\n');
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving leave application.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Construct a message for successful leave application
        StringBuilder message = new StringBuilder();
        message.append("Leave applied successfully for:\n");
        message.append("Employee Name: ").append(employeeName).append("\n");
        message.append("Employee No: ").append(employeeNumber).append("\n");
        message.append("Leave Reason: ").append(leaveReason).append("\n");
        message.append("From: ").append(fromDateString).append("\n");
        message.append("To: ").append(toDateString).append("\n");

        // Display the successful application message
        JOptionPane.showMessageDialog(this, message.toString());
        dispose(); // Close the frame after leave application
    }
}
