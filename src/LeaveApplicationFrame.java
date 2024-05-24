import javax.swing.*;
import java.awt.*;

public class LeaveApplicationFrame extends JFrame {
    private String employeeNumber;
    private JTextField leaveReasonField;
    private JButton applyLeaveButton;

    public LeaveApplicationFrame(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        setTitle("Leave Application");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel leaveReasonLabel = new JLabel("Reason for Leave:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(leaveReasonLabel, gbc);

        leaveReasonField = new JTextField(20);
        gbc.gridx = 1;
        add(leaveReasonField, gbc);

        applyLeaveButton = new JButton("Apply Leave");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(applyLeaveButton, gbc);

        applyLeaveButton.addActionListener(e -> applyLeave());

        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void applyLeave() {
        String leaveReason = leaveReasonField.getText();
        if (leaveReason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a reason for leave.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Logic to apply leave (e.g., writing to a leave application file or database)

        JOptionPane.showMessageDialog(this, "Leave applied successfully for Employee No: " + employeeNumber);
        dispose();
    }
}
