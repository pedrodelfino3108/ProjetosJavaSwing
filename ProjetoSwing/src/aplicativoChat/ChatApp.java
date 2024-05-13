package aplicativoChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatApp extends JFrame implements ActionListener {

	private JTextField messageField;
	private JTextArea chatArea;
	private String userName;

	public ChatApp(String userName) {
		this.userName = userName;

		setTitle("ChatApp");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		chatArea = new JTextArea();
		chatArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatArea);
		add(scrollPane, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());

		messageField = new JTextField();
		inputPanel.add(messageField, BorderLayout.CENTER);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		inputPanel.add(sendButton, BorderLayout.EAST);

		add(inputPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = messageField.getText().trim();
		if (!message.isEmpty()) {
			sendMessage(message);
			messageField.setText("");
		}
	}

	private void sendMessage(String message) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeStamp = sdf.format(new Date());
		chatArea.append("[" + timeStamp + "] " + userName + ": " + message + "\n");
		chatArea.setCaretPosition(chatArea.getDocument().getLength());
	}

	public static void main(String[] args) {
		String userName = JOptionPane.showInputDialog("Enter your name:");
		if (userName != null && !userName.isEmpty()) {
			SwingUtilities.invokeLater(() -> {
				ChatApp chatApp = new ChatApp(userName);
				chatApp.setVisible(true);
			});
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
