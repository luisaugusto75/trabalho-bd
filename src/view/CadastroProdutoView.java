package view;

import model.Produto;
import  java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe que representa a interface gráfica para cadastro de produtos.
 */
public class CadastroProdutoView extends TemplateView {

    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoId;
    private JTextField campoMarca;
    private JButton botaoSalvar;
    private JButton botaoImagem;

    /**
     * Construtor da classe CadastroProdutoView.
     */
    public CadastroProdutoView() {
        super("Cadastro de Produto");
        cadastroProduto();
    }

    /**
     * Configura a interface gráfica para cadastro de produtos.
     */
    private void cadastroProduto() {
        setLayoutDiferente(new GridLayout(5, 2, 10, 10));

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        JLabel labelPreco = new JLabel("Preço:");
        campoPreco = new JTextField();

        JLabel labelId = new JLabel("ID:");
        campoId = new JTextField();

        JLabel labelMarca = new JLabel("Marca:");
        campoMarca = new JTextField();

        botaoSalvar = new JButton("Salvar");
        adicionarAoRodape(botaoSalvar);

        botaoImagem = new JButton("Imagem");




        adicionarConteudo(labelNome);
        adicionarConteudo(campoNome);
        adicionarConteudo(labelPreco);
        adicionarConteudo(campoPreco);
        adicionarConteudo(labelId);
        adicionarConteudo(campoId);
        adicionarConteudo(labelMarca);
        adicionarConteudo(campoMarca);
        adicionarConteudo(botaoImagem);

        botaoSalvar.addActionListener(this::salvarProduto);
    }

    /**
     * Salva o produto após validar os campos.
     *
     * @param e O evento de ação.
     */
    private void salvarProduto(ActionEvent e) {
        String nome = campoNome.getText().trim();
        String precoTexto = campoPreco.getText().trim();
        String idTexto = campoId.getText().trim();
        String marca = campoMarca.getText().trim();

        if (nome.isEmpty() || precoTexto.isEmpty() || idTexto.isEmpty() || marca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nome.length() > 50) {
            JOptionPane.showMessageDialog(this, "Nome do produto deve ter no máximo 50 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (marca.length() > 50) {
            JOptionPane.showMessageDialog(this, "Marca do produto deve ter no máximo 50 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (precoTexto.length() > 10) {
            JOptionPane.showMessageDialog(this, "Preço do produto deve ter no máximo 10 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (idTexto.length() > 10) {
            JOptionPane.showMessageDialog(this, "ID do produto deve ter no máximo 10 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!precoTexto.matches("[0-9]+(\\.[0-9][0-9]?)?")) {
            JOptionPane.showMessageDialog(this, "Preço do produto deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!idTexto.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "ID do produto deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float preco = Float.parseFloat(precoTexto);
            int id = Integer.parseInt(idTexto);

            System.out.println("Produto cadastrado:");
            System.out.println("Nome: " + nome);
            System.out.println("Preço: " + preco);
            System.out.println("ID: " + id);
            System.out.println("Marca: " + marca);

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            campoNome.setText("");
            campoPreco.setText("");
            campoId.setText("");
            campoMarca.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço e ID devem ser numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal para iniciar a interface gráfica.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroProdutoView tela = new CadastroProdutoView();
            tela.setVisible(true);
        });
    }
}