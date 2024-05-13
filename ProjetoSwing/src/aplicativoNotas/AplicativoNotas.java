package aplicativoNotas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AplicativoNotas extends JFrame implements ActionListener {

    private JTextArea areaNotas;

    public AplicativoNotas() {
        setTitle("Aplicativo de Notas");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaNotas = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaNotas);
        add(scrollPane, BorderLayout.CENTER);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(salvarButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salvar")) {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                salvarNotas(arquivo);
            }
        }
    }

    private void salvarNotas(File arquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write(areaNotas.getText());
            JOptionPane.showMessageDialog(this, "Notas salvas com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as notas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplicativoNotas aplicativo = new AplicativoNotas();
            aplicativo.setVisible(true);
        });
    }
}
