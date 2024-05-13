package aplicativoAgenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AgendaSwing extends JFrame implements ActionListener {

    private JTextField nomeField, telefoneField, emailField;
    private JButton adicionarButton, limparButton;
    private JTextArea listaContatos;

    private List<Contato> contatos;

    public AgendaSwing() {
        setTitle("Agenda");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        inputPanel.add(nomeLabel);
        inputPanel.add(nomeField);
        inputPanel.add(telefoneLabel);
        inputPanel.add(telefoneField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        adicionarButton = new JButton("Adicionar");
        adicionarButton.addActionListener(this);
        limparButton = new JButton("Limpar");
        limparButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(adicionarButton);
        buttonPanel.add(limparButton);

        listaContatos = new JTextArea();
        listaContatos.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(listaContatos);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        contatos = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adicionarButton) {
            String nome = nomeField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();

            Contato novoContato = new Contato(nome, telefone, email);
            contatos.add(novoContato);

            atualizarListaContatos();
            limparCampos();
        } else if (e.getSource() == limparButton) {
            limparCampos();
        }
    }

    private void atualizarListaContatos() {
        listaContatos.setText("");
        for (Contato contato : contatos) {
            listaContatos.append(contato.toString() + "\n");
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        telefoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgendaSwing agenda = new AgendaSwing();
            agenda.setVisible(true);
        });
    }
}

class Contato {
    private String nome;
    private String telefone;
    private String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nNome: " + nome + ", Telefone: " + telefone + ", Email: " + email;
    }
}
