package gui;

import app.GlobalConstants;
import models.Donor;
import services.DonorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLIntegrityConstraintViolationException;

public class RegisterDonorPanel extends JPanel {

    public RegisterDonorPanel(JPanel mainPanel, CardLayout cardLayout) {
        setLayout(new BorderLayout());
        setBackground(GlobalConstants.PRIMARY_COLOR);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(GlobalConstants.PRIMARY_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(GlobalConstants.LABEL_FONT);
        usernameLabel.setForeground(GlobalConstants.BASIC_COLOR);

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(GlobalConstants.INPUT_FONT);
        usernameField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(GlobalConstants.LABEL_FONT);
        emailLabel.setForeground(GlobalConstants.BASIC_COLOR);

        JTextField emailField = new JTextField(20);
        emailField.setFont(GlobalConstants.INPUT_FONT);
        emailField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        emailField.setBackground(Color.WHITE);
        emailField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(GlobalConstants.LABEL_FONT);
        nameLabel.setForeground(GlobalConstants.BASIC_COLOR);

        JTextField nameField = new JTextField(20);
        nameField.setFont(GlobalConstants.INPUT_FONT);
        nameField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        nameField.setBackground(Color.WHITE);
        nameField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(GlobalConstants.LABEL_FONT);
        addressLabel.setForeground(GlobalConstants.BASIC_COLOR);

        JTextField addressField = new JTextField(20);
        addressField.setFont(GlobalConstants.INPUT_FONT);
        addressField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        addressField.setBackground(Color.WHITE);
        addressField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(GlobalConstants.LABEL_FONT);
        passwordLabel.setForeground(GlobalConstants.BASIC_COLOR);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(GlobalConstants.INPUT_FONT);
        passwordField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(GlobalConstants.LABEL_FONT);
        confirmPasswordLabel.setForeground(GlobalConstants.BASIC_COLOR);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(GlobalConstants.INPUT_FONT);
        confirmPasswordField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);

        // Add components to formPanel
        int row = 0;

        // Username
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(10, 10, 10, 10);
        gbcButton.anchor = GridBagConstraints.CENTER;

        JButton registerDonorButton = new JButton("Register Donor");
        registerDonorButton.setFont(GlobalConstants.LABEL_FONT);
        registerDonorButton.setBackground(GlobalConstants.BUTTON_BG_COLOR);
        registerDonorButton.setForeground(Color.WHITE);
        registerDonorButton.setPreferredSize(GlobalConstants.BUTTON_SIZE);
        registerDonorButton.setFocusPainted(false);
        registerDonorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Form Validation
        registerDonorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder errorMessages = new StringBuilder();
                String username = usernameField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Username Validation
                if (username.length() < 6) {
                    errorMessages.append("- Username must be at least 6 characters long.\n");
                }

                // Email Validation
                if (!email.matches("^[^@\s]+@[^@\s]+\\.[^@\s]+$")) {
                    errorMessages.append("- Email must be a valid format.\n");
                }

                // Name Validation
                if (name.trim().isEmpty()) {
                    errorMessages.append("- Name cannot be empty.\n");
                }

                // Address Validation
                if (address.trim().isEmpty()) {
                    errorMessages.append("- Address cannot be empty.\n");
                }

                // Password Validation
                if (password.length() < 8) {
                    errorMessages.append("- Password must be at least 8 characters long.\n");
                }

                // Confirm Password Validation
                if (!password.equals(confirmPassword)) {
                    errorMessages.append("- Passwords do not match.\n");
                }

                if (errorMessages.length() > 0) {
                    JOptionPane.showMessageDialog(RegisterDonorPanel.this, errorMessages.toString(), "Validation Errors", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        DonorService donorService = new DonorService();
                        Donor donor = donorService.registerDonorUser(username, password, email, name, address);
                        if (donor == null) {
                            JOptionPane.showMessageDialog(RegisterDonorPanel.this, "An error has occurred. Try again!");
                            return;
                        }
                        JOptionPane.showMessageDialog(RegisterDonorPanel.this, "Donor registered successfully", "Donor registered", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLIntegrityConstraintViolationException exp) {
                        String message = exp.getMessage();
                        if (message.contains("username")) {
                            JOptionPane.showMessageDialog(
                                    RegisterDonorPanel.this,
                                    "Username is already taken. Please choose another.",
                                    "Duplicate Username Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } else if (message.contains("email")) {
                            JOptionPane.showMessageDialog(
                                    RegisterDonorPanel.this,
                                    "Email is already in use. Please use a different email address.",
                                    "Duplicate Email Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    RegisterDonorPanel.this,
                                    "A database constraint was violated: " + message,
                                    "Database Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(RegisterDonorPanel.this, "An unexpected error occurred: " + exp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        gbcButton.gridwidth = GridBagConstraints.REMAINDER;
        buttonPanel.add(registerDonorButton, gbcButton);

        JLabel infoLabel = new JLabel("Already have an account?");
        infoLabel.setFont(GlobalConstants.LABEL_FONT);
        infoLabel.setForeground(GlobalConstants.SECONDARY_COLOR);
        gbcButton.gridwidth = GridBagConstraints.REMAINDER;
        buttonPanel.add(infoLabel, gbcButton);

        JLabel loginLink = new JLabel("<html><u><b>Login</b></u></html>");
        loginLink.setFont(GlobalConstants.LABEL_FONT);
        loginLink.setForeground(GlobalConstants.LINK_COLOR);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "LOGIN");
            }
        });
        gbcButton.gridwidth = GridBagConstraints.REMAINDER;
        buttonPanel.add(loginLink, gbcButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Padding for the form elements
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
}
