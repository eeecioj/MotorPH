 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;   // Text field for entering username
    private JPasswordField passwordField; // Text field for entering password
    private JButton loginButton;       // Button to initiate login process
    private Runnable onSuccess;        // Runnable to execute on successful login

    public LoginFrame(Runnable onSuccess) {
        this.onSuccess = onSuccess;
        setTitle("MotorPH Portal");
        setLayout(null); // Using absolute layout

        // Set background color
        getContentPane().setBackground(new Color(0x16326E));

        // Load the icon image and resize it
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/motorcycle.png"));
        Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        iconLabel.setBounds(160, 20, 80, 80); // Center the icon at the top
        add(iconLabel);

        // Label for "MotorPH Portal"
        JLabel titleLabel = new JLabel("MotorPH Portal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 110, 200, 30); // Center the title below the icon
        add(titleLabel);

        // Label for username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 160, 80, 30);
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel);

        // Text field for entering username
        usernameField = new JTextField();
        usernameField.setBounds(100, 190, 200, 30);
        add(usernameField);

        // Label for password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 230, 80, 30);
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        // Text field for entering password
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 260, 200, 30);
        add(passwordField);

        // Button for initiating login process
        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set login text to bold
        loginButton.setBounds(150, 310, 100, 30);
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

        setSize(400, 400); // Set frame size
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
