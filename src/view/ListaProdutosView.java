package view;

import controller.CarrinhoController;
import controller.Tabela;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import model.Helper;
import java.io.*;

import org.w3c.dom.ls.LSOutput;


import static java.util.logging.Logger.global;

public class ListaProdutosView extends TemplateView {
    HashMap<String, String> userData;
    JScrollPane scrollPane;
    JButton verCarrinho;
    JPanel produtosPanel;
    private JLabel totalPriceLabel;
    private CarrinhoController carrinhoController;
    int quantidadeCarrinho;
    public ListaProdutosView(String titulo) {
        super(titulo);
        setLayout(null);
        setSize(900, 700);

        // Initialize carrinho controller if user is a client
        if (Global.pessoa instanceof Cliente) {
            carrinhoController = new CarrinhoController(Global.getPessoa());
        }

        // Campo de pesquisa
        JTextArea campoPesquisa = new JTextArea();
        campoPesquisa.setBounds(500, 130, 200, 30);
        add(campoPesquisa);

        // Botão Pesquisar
        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setBounds(730, 130, 100, 30);
        add(botaoPesquisar);

        // Painel de produtos
        produtosPanel = new JPanel();
        produtosPanel.setLayout(new BoxLayout(produtosPanel, BoxLayout.Y_AXIS));

        // Add total price display
        totalPriceLabel = new JLabel("Total: R$ 0.00");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel.setBounds(50, 480, 200, 30);
        add(totalPriceLabel);

        // Carregar produtos
        List<Produto> produtos;
        try {
            produtos = Helper.converterProdutos(Global.getDatabase().consulta(Tabela.produto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Adicionar produtos ao painel
        adicionarProdutoPainel(produtos);
        // ScrollPane para a lista de produtos
        scrollPane = new JScrollPane(produtosPanel);
        scrollPane.setBounds(50, 70, 370, 400);
        add(scrollPane);

        // Botão Ver Carrinho
        verCarrinho = null;
        if (Global.pessoa instanceof Cliente) {
            try {
                quantidadeCarrinho = Global.database.getQuantidadeCarrinho(Global.pessoa.getCPF());

                verCarrinho = new JButton("Ver carrinho(" + Global.database.getQuantidadeCarrinho(Global.pessoa.getCPF()) + ")");
                verCarrinho.setBounds(50, 570, 200, 50);
                add(verCarrinho);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Botões de ajuda e sair
        JButton ajuda = new JButton("Ajuda");
        ajuda.setBounds(650, 600, 100, 30);
        add(ajuda);

        JButton sair = new JButton("Sair");
        sair.setBounds(770, 600, 100, 30);
        add(sair);

        sair.addActionListener(e -> dispose());

        // Ação do botão Ver Carrinho
        if (Global.pessoa instanceof Cliente) {
            //JButton finalVerCarrinho = verCarrinho;

            verCarrinho.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView carrinhoView = new CarrinhoView("Carrinho", userData);
                    carrinhoView.setVisible(true);
                    dispose();
                }
            });
        }

        // Ação do botão Pesquisar
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaProdutos(campoPesquisa.getText());
            }
        });

        // Definir posição do título
        setorTitulo.setBounds(300, 10, 400, 30);
        setorTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(setorTitulo);

        // Definir posição do botão de voltar
        botaoVoltar.setBounds(10, 10, 50, 30);
        add(botaoVoltar);

        // Ação do botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView TelaInicialView = new TelaInicialView("", true, Global.pessoa);
                TelaInicialView.setVisible(true);
                dispose();
            }
        });

        // Initialize total price
        atualizaPrecoTotal();
    }

    // Método para atualizar a lista de produtos com base na pesquisa
    private void atualizarListaProdutos(String query) {
        produtosPanel.removeAll();
        List<Produto> produtos;
        try {
            produtos = Global.database.pesquisaProdutos(query);
            System.out.println(produtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adicionarProdutoPainel(produtos);
    }
    private void atualizaPrecoTotal() {
        if (carrinhoController != null) {
            double total = carrinhoController.getCarrinho().calcularValorTotal();
            totalPriceLabel.setText(String.format("Total: R$ %.2f", total));
            try {
                quantidadeCarrinho = Global.database.getQuantidadeCarrinho(Global.pessoa.getCPF());
                verCarrinho.setText("Ver carrinho(" + Global.database.getQuantidadeCarrinho(Global.pessoa.getCPF()) + ")");


            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        TemplateView tela = new ListaProdutosView("Lista de Produtos");
        tela.setVisible(true);
    }
    void adicionarProdutoPainel(List<Produto> produtos)
    {

        for (Produto produto : produtos) {
            System.out.println(produto.getNome());
            JPanel produtoLinha = new JPanel(new BorderLayout());
            produtoLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel nomeProduto = new JLabel(produto.getNome() + " - R$ " + String.format("%.2f", produto.getPreco()));
            nomeProduto.setFont(new Font("Arial", Font.PLAIN, 14));
            produtoLinha.add(nomeProduto, BorderLayout.CENTER);

            if (Global.pessoa instanceof Vendedor) {
                JButton editarButton = new JButton("Editar");
                editarButton.setFont(new Font("Arial", Font.PLAIN, 12));
                editarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new EditarProdutoView("Editar Produto", produto).setVisible(true);
                        atualizarListaProdutos("");
                    }
                });
                produtoLinha.add(editarButton, BorderLayout.WEST);
            }else {

                JButton adicionarButton = new JButton("Adicionar");
                adicionarButton.setFont(new Font("Arial", Font.PLAIN, 12));
                adicionarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("exibir imagem");
                        if (Global.pessoa instanceof Cliente) {
                            System.out.println("é cliente");
                            try {


                                carrinhoController.adicionarProduto(produto, 1);
                                FileInputStream fis = produto.getImagem();
                                byte[] bytes = fis.readAllBytes();
                                fis.getChannel().position(0);
                                ImageIcon imagem = new ImageIcon(bytes);
                                Image img = imagem.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                imagem = new ImageIcon(img);
                                JLabel label = new JLabel(imagem);
                                System.out.println("IMAGEM TA SENDO EXIBIDA TEORICAMENTE");
                                // Exibir no JOptionPane
                                JOptionPane.showMessageDialog(null, label, "Imagem", JOptionPane.PLAIN_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, produto.getNome() + " adicionado ao carrinho!", "Produto Adicionado", JOptionPane.INFORMATION_MESSAGE);

                            }
                            atualizaPrecoTotal();
                        }
                    }
                });
                produtoLinha.add(adicionarButton, BorderLayout.EAST);
            }

            produtosPanel.add(produtoLinha);
        }

        produtosPanel.revalidate();
        produtosPanel.repaint();
    }
}