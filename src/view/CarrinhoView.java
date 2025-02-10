package view;

import model.Carrinho;
import model.Global;
import model.Produto;

import java.util.HashMap;
import java.util.Map;
import controller.CarrinhoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a interface gráfica do carrinho de compras.
 */
public class CarrinhoView extends TemplateView {

    /**
     * Construtor da classe CarrinhoView.
     *
     * @param titulo O título da janela.
     * @param userData Os dados do usuário.
     */
    JPanel produtosPanel;
    public CarrinhoView(String titulo, HashMap<String,String> userData) {
        super(titulo);
        CarrinhoController controller = new CarrinhoController(Global.getPessoa());

        Carrinho carrinhoAtual = controller.getCarrinho();

        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("<html>Lista de Produtos</html>");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new Dimension(400, 100));

        produtosPanel = new JPanel();
        produtosPanel.setLayout(new BoxLayout(produtosPanel, BoxLayout.Y_AXIS));
        CarrinhoController carrinhoController = new CarrinhoController(Global.getPessoa());

        Map<Produto,Integer> conteudo = carrinhoController.retornaProdutos();

        if(carrinhoAtual.getConteudo() != null) {
            System.out.println("Carrinho não está vazio");
            System.out.println(carrinhoAtual.getConteudo());
            for (Map.Entry<Produto, Integer> produto : conteudo.entrySet()) {
                System.out.println("Produto: " + produto.getKey().getNome() + " Quantidade: " + produto.getValue());

                JPanel produtoLinha = new JPanel(new BorderLayout());
                produtoLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JButton nomeProduto = new JButton(produto.getKey().getNome());
                nomeProduto.setFont(new Font("Arial", Font.PLAIN, 14));
                produtoLinha.add(nomeProduto, BorderLayout.BEFORE_FIRST_LINE);

                JButton labelQuantidade = new JButton(produto.getValue() + "");
                labelQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
                produtoLinha.add(labelQuantidade, BorderLayout.CENTER);

                JButton adicionarButton = new JButton("+");
                adicionarButton.setFont(new Font("Arial", Font.PLAIN, 12));
                JButton removerButton = new JButton("-");
                removerButton.setFont(new Font("Arial", Font.PLAIN, 12));

                adicionarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        carrinhoController.adicionarProduto(produto.getKey(), 1 + produto.getValue());
                        atualizarCarrinho(produto.getKey(), produtoLinha, carrinhoController);
                    }
                });
                removerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        carrinhoController.adicionarProduto(produto.getKey(), produto.getValue() - 1);

                        if(produto.getValue() -1<= 0){
                            atualizarCarrinho(produto.getKey(), produtoLinha, carrinhoController);

                        }
                        atualizarCarrinho(produto.getKey(), produtoLinha, carrinhoController);
                    }
                });
                produtoLinha.add(adicionarButton, BorderLayout.EAST);
                produtoLinha.add(removerButton, BorderLayout.WEST);

                produtosPanel.add(produtoLinha);
            }
        } else {
            JLabel semProdutos = new JLabel("Nenhum produto no carrinho");
            produtosPanel.add(semProdutos);
        }

        JScrollPane scrollPane = new JScrollPane(produtosPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaInicial.add(scrollPane, gbc);

        JButton pagar = new JButton("Pagar");
        ajustarBotao(pagar, gbc, telaInicial);

        adicionarConteudo(telaInicial);

        JButton ajuda = new JButton("Ajuda");
        JButton sair = new JButton("Sair");
        adicionarAoRodape(ajuda);
        adicionarAoRodape(sair);

        pagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView carteiraView = new CarteiraView();
                carteiraView.setVisible(true);
                dispose();
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView TelaInicialView = new TelaInicialView("", true,Global.pessoa);
                TelaInicialView.setVisible(true);
                dispose();

            }
        });

    }

    /**
     * Ajusta o botão na interface gráfica.
     *
     * @param botao O botão a ser ajustado.
     * @param gbc As restrições do layout.
     * @param painel O painel onde o botão será adicionado.
     */
    private void ajustarBotao(JButton botao, GridBagConstraints gbc, JPanel painel) {
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        gbc.gridwidth = 2;
        painel.add(botao, gbc);
    }

    /**
     * Atualiza a quantidade de um produto no carrinho.
     *
     * @param produto O produto a ser atualizado.
     * @param quantidadePanel O painel que exibe a quantidade do produto.
     * @param carrinho O controlador do carrinho.
     */
    private void atualizarCarrinho(Produto produto, JPanel quantidadePanel, CarrinhoController carrinho) {
        int quantidade = carrinho.retornaProdutos().getOrDefault(produto, 0);
        if (quantidade <= 0) {
            produtosPanel.remove(quantidadePanel);
            produtosPanel.revalidate();
            produtosPanel.repaint();
        } else {
            JButton quantidadeLabel = (JButton) quantidadePanel.getComponent(1);
            quantidadeLabel.setText(String.valueOf(quantidade));
        }    }

}