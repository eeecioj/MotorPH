import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;   // Text field for entering username
    private JPasswordField passwordField; // Text field for entering password
    private JButton loginButton;       // Button to initiate login process
    private Runnable onSuccess;        // Runnable to execute on successful login

    public LoginFrame(Runnable onSuccess) {
        this.onSuccess = onSuccess;
        setTitle("Employee Management System - Login");
        setLayout(null); // Using absolute layout
        
        // Label for username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 25);
        add(usernameLabel);

        // Text field for entering username
        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 165, 25);
        add(usernameField);

        // Label for password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 80, 25);
        add(passwordLabel);

        // Text field for entering password
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 165, 25);
        add(passwordField);

        // Button for initiating login process
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        add(loginButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if the entered credentials are valid
                    if (authenticateUser(usernameField.getText(), new String(passwordField.getPassword()))) {
                        dispose(); // Close the login frame
                        onSuccess.run(); // Execute the onSuccess runnable
                    } else {
                        // Show error message for incorrect credentials
                        JOptionPane.showMessageDialog(null, "Incorrect username or password!");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(300, 150); // Set frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true); // Make the frame visible
    }

    // Method to authenticate user based on credentials
    private boolean authenticateUser(String username, String password) throws IOException {
        String csvFile = "accounts.csv"; // CSV file containing username and password
        String line;
        String csvSeparator = ","; // Separator used in the CSV file
        
        // Read the CSV file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] account = line.split(csvSeparator); // Split the line into username and password
                // Check if the entered username and password match any account in the CSV file
                if (account[0].equals(username) && account[1].equals(password)) {
                    return true; // Return true if authentication successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Return false if authentication fails
    }
}
