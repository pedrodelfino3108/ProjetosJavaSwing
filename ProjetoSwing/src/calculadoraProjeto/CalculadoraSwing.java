package calculadoraProjeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSwing extends JFrame implements ActionListener {

	private JTextField display;
	private String input = "";
	private double num1 = 0, num2 = 0;
	private char operator;

	public CalculadoraSwing() {
		setTitle("Calculadora");
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		display = new JTextField();
		display.setEditable(false);
		add(display, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 4));

		String[] buttonLabels = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+", "=" };

		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			button.addActionListener(this);
			buttonsPanel.add(button);
		}

		add(buttonsPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if ("0123456789.".contains(action)) {
			input += action;
			display.setText(input);
		} else if ("/*-+".contains(action)) {
			num1 = Double.parseDouble(input);
			operator = action.charAt(0);
			input = "";
		} else if (action.equals("=")) {
			num2 = Double.parseDouble(input);
			double result = 0;
			switch (operator) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				result = num1 / num2;
				break;
			}
			display.setText(String.valueOf(result));
			input = "";
		} else if (action.equals("C")) {
			input = "";
			display.setText("");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CalculadoraSwing calculator = new CalculadoraSwing();
			calculator.setVisible(true);
		});
	}
}
