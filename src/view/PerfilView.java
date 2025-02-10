package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class PerfilView extends TemplateView {

    private Cliente cliente;
    private Vendedor vendedor;
    Pessoa usuario = Global.getPessoa();
    private JLabel nomeField;
    private JLabel emailField;
    private JButton editarDadosButton;

    public PerfilView(String titulo, Cliente cliente) {
        super(titulo);
        this.cliente = cliente;
        initializeProfileComponents(cliente);
        setupProfileLayout();
        setupProfileEvents();
    }
    public PerfilView(String titulo, Vendedor vendedor){
        super(titulo);
        this.vendedor = vendedor;
        initializeProfileComponents(vendedor);
        setupProfileLayout();
        setupProfileEvents();
    }

    private void initializeProfileComponents(Cliente cliente) {
        nomeField = new JLabel(cliente.getNome());
        emailField = new JLabel(cliente.getLogin());
        editarDadosButton = new JButton("Editar Dados");
    }
    private void initializeProfileComponents(Vendedor vendedor){
        nomeField = new JLabel(vendedor.getNome());
        emailField = new JLabel(vendedor.getLogin());
        editarDadosButton = new JButton("Editar Dados");
    }

    private void setupProfileLayout() {
        JPanel profilePanel = new JPanel(null);
        profilePanel.setPreferredSize(new Dimension(400, 300));
        ImageIcon imagemPerfil;
        if(Global.pessoa instanceof Cliente) {
            imagemPerfil = (Helper.fisToImageIcon((Global.getCliente().getImagemPerfil())));
            if (imagemPerfil == null) {
                imagemPerfil = Helper.fisToImageIcon((Global.database.getDefault().getImagemPerfil()));
            }
        }
        else
        {
            imagemPerfil = Helper.fisToImageIcon((Global.database.getDefault().getImagemPerfil()));

        }
        JLabel labelImagem = new JLabel(imagemPerfil);
        labelImagem.setBounds(320, 80, 100, 100);
        profilePanel.add(labelImagem);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(300, 250, 50, 25);
        profilePanel.add(nomeLabel);

        nomeField.setBounds(350, 250, 100, 25);
        profilePanel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(300, 300, 50, 25);
        profilePanel.add(emailLabel);

        emailField.setBounds(350, 300, 100, 25);
        profilePanel.add(emailField);
        if(Global.pessoa instanceof Cliente) {
            editarDadosButton.setBounds(550, 422, 150, 25);
            profilePanel.add(editarDadosButton);

        }
        adicionarConteudo(profilePanel);

    }

    private void setupProfileEvents() {
        editarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarView("Editar Dados", cliente).setVisible(true);
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial", true, usuario);
                telaInicial.setVisible(true);
                dispose();
            }
        });
    }



}