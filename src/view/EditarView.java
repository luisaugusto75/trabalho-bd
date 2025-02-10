package view;

import model.Cliente;
import model.Global;
import model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class EditarView extends TemplateView {

    private Cliente cliente;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton salvarButton;
    private JPasswordField confirmarSenhaField;

    public EditarView(String titulo, Cliente cliente) {
        super(titulo);
        this.cliente = cliente;
        initializeComponents();
        setupLayout();
        setupEvents();
    }

    private void initializeComponents() {
        nomeField = new JTextField(cliente.getNome(), 20);
        emailField = new JTextField(cliente.getLogin(), 20);
        senhaField = new JPasswordField(20);
        salvarButton = new JButton("Salvar");
        confirmarSenhaField = new JPasswordField(20);
    }

    private void setupLayout() {
        JPanel editPanel = new JPanel(null);
        editPanel.setPreferredSize(new Dimension(400, 300));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(240, 100, 50, 25);
        editPanel.add(nomeLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(240, 150, 50, 25);
        editPanel.add(emailLabel);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(240, 200, 50, 25);
        editPanel.add(senhaLabel);

        JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha:");
        confirmarSenhaLabel.setBounds(240, 250, 120, 25);
        editPanel.add(confirmarSenhaLabel);

        nomeField.setBounds(370, 100, 200, 25);
        editPanel.add(nomeField);

        emailField.setBounds(370, 150, 200, 25);
        editPanel.add(emailField);

        senhaField.setBounds(370, 200, 200, 25);
        editPanel.add(senhaField);

        confirmarSenhaField.setBounds(370, 250, 200, 25);
        editPanel.add(confirmarSenhaField);

        salvarButton.setBounds(550, 422, 100, 25);
        editPanel.add(salvarButton);

        adicionarConteudo(editPanel);

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView perfilview = new PerfilView("Perfil",cliente);
                perfilview.setVisible(true);
                dispose();
            }
        });
    }

    protected void setupEvents() {
        if (salvarButton != null) {
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // salvar no BD
                    Pessoa dadosAtuais = Global.getPessoa();
                    Global.setPessoa(new Cliente(nomeField.getText(), dadosAtuais.getCPF(), emailField.getText(), senhaField.getText(), dadosAtuais.getNascimento(), ((Cliente) dadosAtuais).getImagemPerfil()));
                    PerfilView perfil = new PerfilView("Perfil", (Cliente) Global.getPessoa());
                    perfil.setVisible(true);
                    dispose();
                }
            });
        } else {
            System.err.println("salvarButton is null");
        }
    }


}