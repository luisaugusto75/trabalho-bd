package view;

import controller.VendedorController;
import model.Administrador;
import model.Pessoa;
import model.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenciarFuncionariosView extends TemplateView {
    private JTextField campoPesquisa;
    private JScrollPane scrollPane;
    private JPanel vendedoresPanel;
    private List<Vendedor> vendedores = new ArrayList<>();

    private Pessoa usuarioAtual;

    public GerenciarFuncionariosView(String titulo, Pessoa usuarioAtual) {
        super(titulo);
        setLayout(null);
        setSize(900, 700);
        this.usuarioAtual = usuarioAtual;

        // Campo de pesquisa
        campoPesquisa = new JTextField();
        campoPesquisa.setBounds(500, 130, 200, 30);
        add(campoPesquisa);

        // Botão Pesquisar
        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setBounds(730, 130, 100, 30);
        add(botaoPesquisar);

        // Painel de vendedores
        vendedoresPanel = new JPanel();
        vendedoresPanel.setLayout(new BoxLayout(vendedoresPanel, BoxLayout.Y_AXIS));

        //Botao voltar
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(50, 500, 100, 30);
        add(voltar);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaInicialView("Menu",true, usuarioAtual).setVisible(true);
            }
        });

        // Carregar vendedores
        try {
            vendedores = VendedorController.pesquisarVendedores("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        carregarVendedoresNoPainel(vendedores);

        // ScrollPane para a lista de vendedores
        scrollPane = new JScrollPane(vendedoresPanel);
        scrollPane.setBounds(50, 70, 370, 400);
        add(scrollPane);

        // Ação do botão Pesquisar
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaVendedores(campoPesquisa.getText());
            }
        });
    }

    // Método para carregar vendedores no painel
    private void carregarVendedoresNoPainel(List<Vendedor> vendedores) {
    vendedoresPanel.removeAll();
    for (Vendedor vendedor : vendedores) {
        JPanel vendedorLinha = new JPanel(new GridBagLayout());
        vendedorLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomeVendedor = new JLabel(vendedor.getNome() + " - CPF: " + vendedor.getCPF());
        nomeVendedor.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        vendedorLinha.add(nomeVendedor, gbc);




        JButton editarButton = new JButton("Editar");
        editarButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EditarVendedorView editarVendedorView = new EditarVendedorView("Editar Vendedor", vendedor,(Administrador)usuarioAtual);
                editarVendedorView.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        vendedorLinha.add(editarButton, gbc);

        JButton deletarButton = new JButton("Deletar");
        deletarButton.setFont(new Font("Arial", Font.PLAIN, 12));
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para deletar o vendedor
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        vendedorLinha.add(deletarButton, gbc);

        vendedoresPanel.add(vendedorLinha);
    }
    vendedoresPanel.revalidate();
    vendedoresPanel.repaint();
}

    // Método para atualizar a lista de vendedores com base na pesquisa
    // Método para atualizar a lista de vendedores com base na pesquisa
    private void atualizarListaVendedores(String query) {
        try {
            System.out.println("Pesquisando vendedores com query: " + query); // Log para depuração
            vendedores = VendedorController.pesquisarVendedores(query);
            if (vendedores != null && !vendedores.isEmpty()) {
                System.out.println("Vendedores encontrados: " + vendedores.size()); // Log para depuração
                carregarVendedoresNoPainel(vendedores);
            } else {
                System.out.println("Nenhum vendedor encontrado."); // Log para depuração
                JOptionPane.showMessageDialog(this, "Nenhum vendedor encontrado.", "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
                vendedoresPanel.removeAll();
                vendedoresPanel.revalidate();
                vendedoresPanel.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar vendedores.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}