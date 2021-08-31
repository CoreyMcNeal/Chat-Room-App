package ClientSide;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ClientGUI implements ActionListener {
    
    private JFrame frame;

    private JPanel introPanel;                  // introductory screen, with button to connect to server
    private JButton introConnectButton;
    private JLabel introLabel;

    private JPanel chatPanel;                   // chat screen...
    private JTextArea chatArea;
    private JScrollPane scrollPane;
    
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
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private void buildIntroPanel() {
        introLabel = new JLabel("Welcome to the JChat Application!", SwingConstants.CENTER);

        introConnectButton = new JButton("Connect to Server");

        introConnectButton.addActionListener(this);

        introPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        introPanel.setLayout(new GridLayout(0, 1));

        introPanel.add(introLabel);
        introPanel.add(introConnectButton);
    }

    private void buildChatPanel() {
        chatPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        chatPanel.setLayout(new GridLayout(0, 1));

        chatArea = new JTextArea();
        scrollPane = new JScrollPane(chatArea);
        chatArea.setEditable(false);
        
        chatPanel.add(scrollPane);
    }

    private void showChatPanel() {

        frame.remove(introPanel);
        frame.add(chatPanel, BorderLayout.CENTER);

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
        }
        
    }
}
