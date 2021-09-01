package ClientSide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientGUI implements ActionListener, Runnable {
    
    private JFrame frame;
    private ClientLogic clientLogic;
    private ExecutorService thread = Executors.newFixedThreadPool(2);
    JOptionPane connectingWindow = new JOptionPane();

    private JPanel introPanel;                  // introductory screen, with button to connect to server
    private JButton introConnectButton;
    private JLabel introLabel;
    private JLabel introIPLabel;
    private JTextField introIPEntry;
    private JLabel introNameLabel;
    private JTextField introNameEntry;
    private GridBagConstraints constraints;

    private JPanel chatPanel;                   // chat screen, with JTextField for chat messages, an entry box for
    private JLabel chatWelcomeLabel;            // inputs, and an exit button.
    private JTextArea chatArea;                 
    private JScrollPane scrollPane;
    private JLabel chatMessageHereLabel;
    private JTextField chatEntry;
    private JButton exitButton;
    private GridBagConstraints chatConstraints;

    public void start() {
        buildPanels();
    }

    private void buildPanels() {               // Creates the frame and two panels.
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

    private void buildIntroPanel() {                    //Builds introPanel

        introPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        introPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();


        introLabel = new JLabel("Welcome to the JChat Application!", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = 10;
        introPanel.add(introLabel, constraints);

        introIPLabel = new JLabel("Enter Server IP: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        introPanel.add(introIPLabel, constraints);

        introIPEntry = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 2;
        introIPEntry.setPreferredSize(new Dimension(200, 20));
        introPanel.add(introIPEntry, constraints);

        introNameLabel = new JLabel("Enter Username: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        introPanel.add(introNameLabel, constraints);

        introNameEntry = new JTextField();
        introNameEntry.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 4;
        introNameEntry.setPreferredSize(new Dimension(200, 20));
        introPanel.add(introNameEntry, constraints);
        

        introConnectButton = new JButton("Connect to Server");
        introConnectButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 5;
        introConnectButton.setPreferredSize(new Dimension(200, 100));
        introPanel.add(introConnectButton, constraints);
    }

    private void buildChatPanel() {                         // Builds chatPanel

        chatPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        chatPanel.setLayout(new GridBagLayout());
        chatConstraints = new GridBagConstraints();

        chatWelcomeLabel = new JLabel("Connected to Chat!");
        chatConstraints.gridx = 0;
        chatConstraints.gridy = 0;
        chatPanel.add(chatWelcomeLabel, chatConstraints);

        chatArea = new JTextArea();
        scrollPane = new JScrollPane(chatArea);
        chatArea.setEditable(false);
        chatConstraints.gridx = 0;
        chatConstraints.gridy = 1;
        scrollPane.setPreferredSize(new Dimension(500, 375));
        chatPanel.add(scrollPane, chatConstraints);

        chatMessageHereLabel = new JLabel("Enter your message below:");
        chatConstraints.gridx = 0;
        chatConstraints.gridy = 2;
        chatPanel.add(chatMessageHereLabel, chatConstraints);

        chatEntry = new JTextField();
        chatEntry.addActionListener(this);
        chatConstraints.gridx = 0;
        chatConstraints.gridy = 3;
        chatEntry.setPreferredSize(new Dimension(350, 50));
        chatPanel.add(chatEntry, chatConstraints);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        chatConstraints.gridx = 0;
        chatConstraints.gridy = 4;
        exitButton.setPreferredSize(new Dimension(100, 50));
        chatPanel.add(exitButton, chatConstraints);
    }

    private void showChatPanel() {                          // Transitions to the chatPanel

        frame.remove(introPanel);
        frame.add(chatPanel, BorderLayout.CENTER);
        chatEntry.requestFocus();

        refreshFrame();
    }

    public void addToChat(String chatMessage) {
        chatArea.append(chatMessage + "\n");
    }

    private void refreshFrame() {           // Refreshes the frame so changes can be seen
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {   // Events for each respective Button pressed
        
        if (e.getSource() == introConnectButton || e.getSource() == introNameEntry) {
            introConnectButton.setText("Connecting...");
            refreshFrame();
            thread.execute(this);

        } else if (e.getSource() == chatEntry) {

            try {
                clientLogic.sendMessage(chatEntry.getText());
            } catch (Exception p) {
                JOptionPane.showMessageDialog(null, "Couldn't send message.");
            }
            chatEntry.setText("");

        } else if (e.getSource() == exitButton) {
            frame.dispose();
            System.exit(0);

        }
    }

    @Override
    public void run() {
        try {
            clientLogic = new ClientLogic(introNameEntry.getText(), this, introIPEntry.getText());
            thread.execute(clientLogic);
            showChatPanel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Couldn't Connect to Server");
            introConnectButton.setText("Connect to Server");
            refreshFrame();
        }
    }
}
