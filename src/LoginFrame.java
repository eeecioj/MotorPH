import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Runnable onSuccess;

    public LoginFrame(Runnable onSuccess) {
        this.onSuccess = onSuccess;
        setTitle("Employee Management System - Login");
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 165, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 165, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (authenticateUser(usernameField.getText(), new String(passwordField.getPassword()))) {
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

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean authenticateUser(String username, String password) throws IOException {
        String csvFile = "accounts.csv";
        String line;
        String csvSeparator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] account = line.split(csvSeparator);
                if (account[0].equals(username) && account[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
