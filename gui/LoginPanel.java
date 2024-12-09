package gui;

import app.GlobalConstants;
import services.UserService;
import utils.ResourceUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    public LoginPanel(JPanel mainPanel, CardLayout cardLayout) {
        setLayout(new GridBagLayout());
        setBackground(GlobalConstants.PRIMARY_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; // Center components

        JLabel loginImage = ResourceUtils.loadImage(GlobalConstants.LOGIN_IMAGE_PATH);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(loginImage, gbc);

        JLabel titleLabel = new JLabel("Welcome to Donation System");
        titleLabel.setFont(GlobalConstants.TITLE_FONT);
        titleLabel.setForeground(GlobalConstants.SECONDARY_COLOR);
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Title spans across the remaining space
        add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(GlobalConstants.LABEL_FONT);
        usernameLabel.setForeground(GlobalConstants.BASIC_COLOR);
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(GlobalConstants.INPUT_FONT);
        usernameField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(GlobalConstants.LABEL_FONT);
        passwordLabel.setForeground(GlobalConstants.BASIC_COLOR);
        gbc.gridwidth = 1;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(GlobalConstants.INPUT_FONT);
        passwordField.setPreferredSize(GlobalConstants.TEXT_FIELD_SIZE);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(GlobalConstants.TEXT_FIELD_BORDER);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(GlobalConstants.LABEL_FONT);
        loginButton.setBackground(GlobalConstants.BUTTON_BG_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(GlobalConstants.BUTTON_SIZE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(actionEvent -> {
            UserService userService = new UserService();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (userService.loginUser(username, password) != null) {
                JOptionPane.showMessageDialog(this, "Login Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(loginButton, gbc);

        JLabel infoLabel = new JLabel("Don't have an account?");
        infoLabel.setFont(GlobalConstants.LABEL_FONT);
        infoLabel.setForeground(GlobalConstants.SECONDARY_COLOR);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(infoLabel, gbc);

        JLabel registerDonorLink = new JLabel("<html><u><b>Register as a Donor</b></u></html>");
        registerDonorLink.setFont(GlobalConstants.LABEL_FONT);
        registerDonorLink.setForeground(GlobalConstants.LINK_COLOR);
        registerDonorLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerDonorLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                cardLayout.show(mainPanel, "REGISTER_DONOR");
            }
        });
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(registerDonorLink, gbc);

        JLabel registerAssociationLink = new JLabel("<html><u><b>Register as an Association</b></u></html>");
        registerAssociationLink.setFont(GlobalConstants.LABEL_FONT);
        registerAssociationLink.setForeground(GlobalConstants.LINK_COLOR);
        registerAssociationLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerAssociationLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                cardLayout.show(mainPanel, "REGISTER_ASSOCIATION");
            }
        });
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(registerAssociationLink, gbc);
    }
}
