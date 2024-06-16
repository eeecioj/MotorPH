import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel payrollPanel;
    private JPanel timesheetPanel;
    private JPanel leaveRequestPanel;
    private String userRole;

    public MainFrame(String userRole) {
        this.userRole = userRole;

        setTitle("MotorPH Employee Portal");
        setLayout(new BorderLayout());

        // Create a sidebar panel for the menu
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridBagLayout());
        sidebar.setBackground(new Color(0x16326E));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH; // Aligns components to the top

        // Create a panel to hold the image and name
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridBagLayout());
        userPanel.setOpaque(false); // Make it transparent to inherit the background color

        // Load the user image and name
        JLabel imageLabel = new JLabel();
        JLabel nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);

        // Load user image and first name
        loadUserImageAndName(imageLabel, nameLabel);

        // Add image and name to the user panel
        GridBagConstraints userGbc = new GridBagConstraints();
        userGbc.gridy = 0;
        userGbc.gridx = 0;
        userGbc.insets = new Insets(10, 0, 10, 0);
        userGbc.anchor = GridBagConstraints.CENTER;
        userPanel.add(imageLabel, userGbc);

        userGbc.gridy = 1;
        userPanel.add(nameLabel, userGbc);

        // Add user panel to the sidebar
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        sidebar.add(userPanel, gbc);

        // Add a sign-out button at the bottom
        JButton signOutButton = new JButton("Sign Out");
        gbc.gridy++; // Increment the gridy value
        sidebar.add(signOutButton, gbc);

        // Add space after the sign-out button
        gbc.gridy++;
        sidebar.add(Box.createVerticalStrut(20), gbc);

        // Conditionally add Employee button to the sidebar
        EmployeeButton employeeButton = null;
        if (!"employee".equalsIgnoreCase(userRole)) {
            employeeButton = new EmployeeButton(cardLayout, mainPanel);
            gbc.gridy++;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.CENTER;
            sidebar.add(employeeButton, gbc);
        }

        // Add Payroll button to the sidebar
        JButton payrollButton = new JButton("Payroll");
        gbc.gridy++;
        sidebar.add(payrollButton, gbc);

        // Add Timesheet button to the sidebar
        JButton timesheetButton = new JButton("Timesheet");
        gbc.gridy++;
        sidebar.add(timesheetButton, gbc);

        // Add Leave Request button to the sidebar
        JButton leaveRequestButton = new JButton("Leave Request");
        gbc.gridy++;
        sidebar.add(leaveRequestButton, gbc);

        add(sidebar, BorderLayout.WEST);

        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create panels for Employee, Payroll, and Leave Request
        if (!"employee".equalsIgnoreCase(userRole)) {
            EmployeePanel employeePanel = new EmployeePanel();
            mainPanel.add(employeePanel, "Employee");
        }
        payrollPanel = createPayrollPanel();
        timesheetPanel = createTimesheetPanel();
        leaveRequestPanel = createLeaveRequestPanel();

        // Add these panels to the main panel
        mainPanel.add(payrollPanel, "Payroll");
        mainPanel.add(timesheetPanel, "Timesheet");
        mainPanel.add(leaveRequestPanel, "Leave Request");

        add(mainPanel, BorderLayout.CENTER);

        // Action listeners for sidebar buttons
        if (employeeButton != null) {
            employeeButton.addActionListener(e -> cardLayout.show(mainPanel, "Employee"));
        }
        payrollButton.addActionListener(e -> cardLayout.show(mainPanel, "Payroll"));
        timesheetButton.addActionListener(e -> cardLayout.show(mainPanel, "Timesheet"));
        leaveRequestButton.addActionListener(e -> cardLayout.show(mainPanel, "Leave Request"));

        // Add action listener to the sign-out button
        signOutButton.addActionListener(e -> {
            // Close the current frame
            dispose();
            // Show the login frame
            SwingUtilities.invokeLater(() -> new LoginFrame(() -> {
                // Callback function for successful login
                new MainFrame(userRole);
            }));
        });

        // Set frame properties
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadUserImageAndName(JLabel imageLabel, JLabel nameLabel) {
        try {
            // Load user image
            BufferedImage userImage = ImageIO.read(new File("userimage.png"));
            if (userImage != null) {
                Image circularImage = getCircularImage(userImage, 75);
                imageLabel.setIcon(new ImageIcon(circularImage));
            }

            // Load user first name
            List<String> lines = Files.readAllLines(Paths.get("accounts.csv"));
            if (lines.size() > 2) {
                String[] userDetails = lines.get(2).split(",");
                String firstName = userDetails[2]; // Assuming first name is at index 2
                String lastName = userDetails[1];  // Assuming last name is at index 1
                nameLabel.setText(firstName + " " + lastName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image getCircularImage(BufferedImage image, int diameter) {
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a circle and set it as the clip
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2d.setClip(circle);
        g2d.drawImage(image, 0, 0, diameter, diameter, null); // Draw the image within the circle
        g2d.dispose();

        return circularImage;
    }

    private JPanel createPayrollPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add your payroll management UI components here
        panel.add(new JLabel("Payroll Panel", SwingConstants.CENTER), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTimesheetPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add your timesheet management UI components here
        panel.add(new JLabel("Timesheet Panel", SwingConstants.CENTER), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createLeaveRequestPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add your leave request UI components here
        panel.add(new JLabel("Leave Request Panel", SwingConstants.CENTER), BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame(() -> {
            LoginFrame loginFrame = new LoginFrame(() -> {});
            String userRole = loginFrame.getUserRole();
            new MainFrame(userRole);
        }));
    }

    public class EmployeeButton extends JButton {
        public EmployeeButton(CardLayout cardLayout, JPanel mainPanel) {
            super("Employee");
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "Employee");
                }
            });
        }
    }
}
