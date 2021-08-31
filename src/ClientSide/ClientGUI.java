package ClientSide;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientGUI implements ActionListener {
    
    private JFrame frame;

    private JPanel introPanel;                  // introductory screen, with button to connect to server
    private JButton introConnectButton;
    private JLabel introLabel;
    GridBagConstraints constraints;

    private JPanel chatPanel;                   // chat screen...
    private JTextArea chatArea;
    private JScrollPane scrollPane;
    private JTextField chatEntry;
    private JButton exitButton;
    GridBagConstraints chatConstraints;
    
    public ClientGUI() {
        
    }

    public void start() {
        buildPanels();
    }

    private void buildPanels() {
        frame = new JFrame();

        introPanel = new JPanel();
        chatPanel = new JPanel();
        
        buildIntroPanel();
        buildChatPanel();

        frame.add(introPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("JChat Application");
        frame.setSize(550, 650);
        frame.setVisible(true);
    }

    private void buildIntroPanel() {
        introLabel = new JLabel("Welcome to the JChat Application!", SwingConstants.CENTER);

        introConnectButton = new JButton("Connect to Server");

        introConnectButton.addActionListener(this);

        introPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        introPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        
        constraints.gridx = 0;
        constraints.gridy = 0;
        introPanel.add(introLabel, constraints);

    
        constraints.gridx = 0;
        constraints.gridy = 1;
        introConnectButton.setPreferredSize(new Dimension(200, 100));
        introPanel.add(introConnectButton, constraints);
    }

    private void buildChatPanel() {
        chatPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        chatPanel.setLayout(new GridBagLayout());
        chatConstraints = new GridBagConstraints();

        chatArea = new JTextArea();
        scrollPane = new JScrollPane(chatArea);
        chatEntry = new JTextField();
        exitButton = new JButton("Exit");

        chatEntry.addActionListener(this);
        exitButton.addActionListener(this);
        chatArea.setEditable(false);

        chatConstraints.gridx = 0;
        chatConstraints.gridy = 0;
        scrollPane.setPreferredSize(new Dimension(500, 375));
        chatPanel.add(scrollPane, chatConstraints);

        chatConstraints.gridx = 0;
        chatConstraints.gridy = 1;
        chatEntry.setPreferredSize(new Dimension(350, 50));
        chatPanel.add(chatEntry, chatConstraints);

        chatConstraints.gridx = 0;
        chatConstraints.gridy = 2;
        exitButton.setPreferredSize(new Dimension(100, 50));
        chatPanel.add(exitButton, chatConstraints);
    }

    private void showChatPanel() {

        frame.remove(introPanel);
        frame.add(chatPanel, BorderLayout.CENTER);
        chatEntry.requestFocus();

        refreshFrame();
    }

    private void refreshFrame() {
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == introConnectButton) {
            showChatPanel();
        } else if (e.getSource() == chatEntry) {
            // Add Client to Server code here
            
            chatEntry.setText("");
        } else if (e.getSource() == exitButton) {
            frame.dispose();
            System.exit(0);
        }
    }
}
