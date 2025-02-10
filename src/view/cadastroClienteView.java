package view;

import controller.ClienteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class cadastroClienteView extends TemplateView {

    private FileInputStream fis;

    public cadastroClienteView(String titulo) {
        super(titulo);
        JPanel telaCadastroCliente = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Cadastro");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaCadastroCliente.add(header, gbc);

        JLabel nome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(25);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(nome, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoNome, gbc);

        JLabel cpf = new JLabel("CPF:");
        JTextField campoCpf = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(cpf, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoCpf, gbc);

        JLabel email = new JLabel("Email:");
        JTextField campoEmail = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(email, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoEmail, gbc);

        JLabel senha = new JLabel("Senha:");
        JTextField campoSenha = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(senha, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoSenha, gbc);

        JLabel nascimento = new JLabel("Data de Nascimento (dd/mm/yyyy):");
        JTextField campoNascimento = new JTextField(8);

        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(nascimento, gbc);

        gbc.gridx = 1;
        telaCadastroCliente.add(campoNascimento, gbc);



        JLabel telefone = new JLabel("Telefone:");
        JTextField campoTelefone = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(telefone, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoTelefone, gbc);

        JButton botaoImagem = new JButton("Foto de Perfil");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        telaCadastroCliente.add(botaoImagem, gbc);

        JButton cadastrar = new JButton("Cadastrar");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        telaCadastroCliente.add(cadastrar, gbc);


        JButton cancelar = new JButton("Cancelar");
        JButton ajuda = new JButton("Ajuda");
        adicionarConteudo(telaCadastroCliente);
        adicionarAoRodape(cancelar);
        adicionarAoRodape(ajuda);


        botaoImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImagem();
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial",false);
                telaInicial.setVisible(true);
                dispose();
            }
        });
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText().trim();
                String cpf = campoCpf.getText().trim();
                String email = campoEmail.getText().trim();
                String senha = campoSenha.getText().trim();
                String nascimento = campoNascimento.getText().trim();
                String telefone = campoTelefone.getText().trim();

                // Verificação do nome
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificação do CPF
                if (!cpf.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(null, "CPF deve conter 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificação do email
                if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    JOptionPane.showMessageDialog(null, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificação da senha
                if (senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Senha não pode estar vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificação da data de nascimento
                if (!nascimento.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Data de nascimento deve estar no formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificação do telefone
                if (!telefone.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Telefone deve conter apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Formatação da data de nascimento
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataNascimento;
                try {
                    dataNascimento = LocalDate.parse(nascimento, formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data de nascimento inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    ClienteController clienteController = new ClienteController();
                    clienteController.criaCliente(nome, cpf, email, senha, Date.valueOf(dataNascimento),fis);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    void addImagem()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma imagem");
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fis = new FileInputStream(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
