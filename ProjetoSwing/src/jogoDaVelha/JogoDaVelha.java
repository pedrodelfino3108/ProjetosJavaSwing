package jogoDaVelha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame implements ActionListener {

	private JButton[][] botoes;
	private char[][] tabuleiro;
	private char jogadorAtual;
	private JLabel statusLabel;

	public JogoDaVelha() {
		setTitle("Jogo da Velha");
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel tabuleiroPanel = new JPanel();
		tabuleiroPanel.setLayout(new GridLayout(3, 3));

		botoes = new JButton[3][3];
		tabuleiro = new char[3][3];
		jogadorAtual = 'X';

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botoes[i][j] = new JButton();
				botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				botoes[i][j].addActionListener(this);
				tabuleiroPanel.add(botoes[i][j]);
			}
		}

		add(tabuleiroPanel, BorderLayout.CENTER);

		statusLabel = new JLabel("Vez do jogador: X");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(statusLabel, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botaoClicado = (JButton) e.getSource();
		int linha = -1, coluna = -1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (botoes[i][j] == botaoClicado) {
					linha = i;
					coluna = j;
				}
			}
		}

		if (linha != -1 && coluna != -1 && tabuleiro[linha][coluna] == '\u0000') {
			tabuleiro[linha][coluna] = jogadorAtual;
			botoes[linha][coluna].setText(Character.toString(jogadorAtual));

			if (verificarVencedor()) {
				statusLabel.setText("Jogador " + jogadorAtual + " venceu!");
				desabilitarBotoes();
			} else if (verificarEmpate()) {
				statusLabel.setText("Empate!");
				desabilitarBotoes();
			} else {
				jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
				statusLabel.setText("Vez do jogador: " + jogadorAtual);
			}
		}
	}

	private boolean verificarVencedor() {
 		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
				return true;
			}
			if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
				return true;
			}
		}
		if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual)
				|| (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual
						&& tabuleiro[2][0] == jogadorAtual)) {
			return true;
		}
		return false;
	}

	private boolean verificarEmpate() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == '\u0000') {
					return false;
				}
			}
		}
		return true;
	}

	private void desabilitarBotoes() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botoes[i][j].setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JogoDaVelha jogo = new JogoDaVelha();
			jogo.setVisible(true);
		});
	}
}
