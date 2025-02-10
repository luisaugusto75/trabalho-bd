package view;

import controller.VendedorController;
import model.Administrador;
import model.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarVendedorView extends TemplateView {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField salarioField;
    private JTextField comissaoField;
    private Vendedor vendedor;

    private Administrador usuario;
    public EditarVendedorView(String titulo, Vendedor vendedor, Administrador administrador){
        super(titulo);

        this.vendedor = vendedor;
        this.usuario = administrador;
        setTitle("Editar Vendedor");
        setSize(400, 400);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLabel, gbc);

        nomeField = new JTextField(vendedor.getNome());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(nomeField, gbc);

        JLabel cpfLabel = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(cpfLabel, gbc);

        cpfField = new JTextField(vendedor.getCPF());
        cpfField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(cpfField, gbc);

        JLabel salarioLabel = new JLabel("Salário:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(salarioLabel, gbc);

        salarioField = new JTextField(String.valueOf(vendedor.getSalario()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(salarioField, gbc);

        JLabel comissaoLabel = new JLabel("Comissão:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(comissaoLabel, gbc);

        comissaoField = new JTextField(String.valueOf(vendedor.getComissao()));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(comissaoField, gbc);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarVendedor();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(salvarButton, gbc);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarFuncionariosView gerenciarFuncionariosView = new GerenciarFuncionariosView("Gerenciar Funcionários",usuario);
                gerenciarFuncionariosView.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(cancelarButton, gbc);
    }

    private void salvarVendedor() {
        String nome = nomeField.getText().trim();
        String salarioTexto = salarioField.getText().trim();
        String comissaoTexto = comissaoField.getText().trim();

        // Verificação do nome
        if (nome.isEmpty() || nome.length() > 50) {
            JOptionPane.showMessageDialog(this, "Nome deve ter entre 1 e 50 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação do salário
        double salario;
        try {
            salario = Double.parseDouble(salarioTexto);
            if (salario <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salário deve ser um número válido e maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação da comissão
        double comissao;
        try {
            comissao = Double.parseDouble(comissaoTexto);
            if (comissao < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Comissão deve ser um número válido e não negativa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        vendedor.setNome(nome);
        vendedor.setSalario(salario);
        vendedor.setComissao(comissao);

        try {
            VendedorController.atualizarVendedor(vendedor);
            JOptionPane.showMessageDialog(this, "Vendedor atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            GerenciarFuncionariosView gerenciarFuncionariosView = new GerenciarFuncionariosView("Gerenciar Funcionários", usuario);
            gerenciarFuncionariosView.setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar vendedor.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}