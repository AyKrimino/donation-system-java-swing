package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    public CardLayout cardLayout;

    public MainFrame() {
        setTitle("Donation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create panels to switch between
        JPanel loginPanel = new LoginPanel(mainPanel, cardLayout);
        JPanel registerDonorPanel = new RegisterDonorPanel(mainPanel, cardLayout);
        JPanel registerAssociationPanel = new RegisterAssociationPanel(mainPanel, cardLayout);

        // Add panels to mainPanel
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerDonorPanel, "REGISTER_DONOR");
        mainPanel.add(registerAssociationPanel, "REGISTER_ASSOCIATION");

        // Add the main panel to the frame
        add(mainPanel);

        // Show the initial panel
        cardLayout.show(mainPanel, "LOGIN");
    }
}
