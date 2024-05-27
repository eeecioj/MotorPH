import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker; // Requires the SwingX library

public class LeaveApplicationFrame extends JFrame {
    private String employeeNumber;
    private String employeeName;
    private JLabel employeeInfoLabel;
    private JComboBox<String> leaveReasonComboBox;
    private JLabel fromDateLabel;
    private JLabel toDateLabel;
    private JXDatePicker fromDatePicker;
    private JXDatePicker toDatePicker;
    private JButton applyLeaveButton;

    public LeaveApplicationFrame(String employeeNumber, String employeeName) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        setTitle("Leave Application");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Employee Info
        employeeInfoLabel = new JLabel("<html><b>Employee Name:</b> " + employeeName + "<br><b>Employee No:</b> " + employeeNumber + "</html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(employeeInfoLabel, gbc);

        // Leave Reason
        JLabel leaveReasonLabel = new JLabel("Reason for Leave:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(leaveReasonLabel, gbc);

        // Leave Reason Combo Box
        String[] leaveReasons = {"Casual Leave", "Bereavement Leave", "Maternity Leave", "Paternity Leave", "Sick Leave" };
        leaveReasonComboBox = new JComboBox<>(leaveReasons);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(leaveReasonComboBox, gbc);

        // Date From
        fromDateLabel = new JLabel("Date From:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(fromDateLabel, gbc);

        fromDatePicker = new JXDatePicker();
        gbc.gridx = 1;
        add(fromDatePicker, gbc);

        // Date To
        toDateLabel = new JLabel("Date To:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(toDateLabel, gbc);

        toDatePicker = new JXDatePicker();
        gbc.gridx = 1;
        add(toDatePicker, gbc);

        // Apply Leave Button
        applyLeaveButton = new JButton("Apply Leave");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(applyLeaveButton, gbc);

        applyLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyLeave();
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void applyLeave() {
        String leaveReason = (String) leaveReasonComboBox.getSelectedItem();

        Date fromDate = fromDatePicker.getDate();
        Date toDate = toDatePicker.getDate();

        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(this, "Please select the leave duration.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (toDate.before(fromDate)) {
            JOptionPane.showMessageDialog(this, "The 'Date To' must be after 'Date From'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Logic to apply leave (e.g., writing to a leave application file or database)

        StringBuilder message = new StringBuilder();
        message.append("Leave applied successfully for:\n");
        message.append("Employee Name: ").append(employeeName).append("\n");
        message.append("Employee No: ").append(employeeNumber).append("\n");
        message.append("Leave Reason: ").append(leaveReason).append("\n");
        message.append("From: ").append(fromDate).append("\n");
        message.append("To: ").append(toDate).append("\n");

        JOptionPane.showMessageDialog(this, message.toString());
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LeaveApplicationFrame("12345", "John Doe").setVisible(true);
        });
    }
}
