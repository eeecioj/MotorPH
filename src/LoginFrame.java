import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Runnable onSuccess;
    private String userRole;

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
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(150, 310, 100, 30);
        add(loginButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userRole = authenticateUser(usernameField.getText(), new String(passwordField.getPassword()));
                    if (userRole != null) {
                        dispose();
                        onSuccess.run();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect username or password!");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String authenticateUser(String username, String password) throws IOException {
        String csvFile = "accounts.csv";
        String line;
        String csvSeparator = ",";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] account = line.split(csvSeparator);
                if (account.length >= 6 && account[4].equals(username) && account[5].equals(password)) {
                    return account[3];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserRole() {
        return userRole;
    }

    // Method to load image icon with error handling
    protected static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = LoginFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
