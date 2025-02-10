package view;

import controller.VendaController;
import model.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class CarteiraView extends TemplateView {
    private HashMap<String,String> info;
    private String numero;
    public CarteiraView() {
        super("Carteira de Pagamentos");
        criarInterface();
    }

    private void criarInterface() {
    setorConteudo.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    JRadioButton botaoCredito = new JRadioButton("Cartão de Crédito");
    botaoCredito.setFont(new Font("Arial", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 0;
    setorConteudo.add(botaoCredito, gbc);

    JRadioButton botaoDebito = new JRadioButton("Cartão de Débito");
    botaoDebito.setFont(new Font("Arial", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 1;
    setorConteudo.add(botaoDebito, gbc);

    JRadioButton botaoPix = new JRadioButton("PIX");
    botaoPix.setFont(new Font("Arial", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 2;
    setorConteudo.add(botaoPix, gbc);

    ButtonGroup grupoBotoes = new ButtonGroup();
    grupoBotoes.add(botaoCredito);
    grupoBotoes.add(botaoDebito);
    grupoBotoes.add(botaoPix);

    JPanel painelCartao = new JPanel();
    painelCartao.setLayout(new GridLayout(5, 2, 10, 10));
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.weighty = 0.5;
    setorConteudo.add(painelCartao, gbc);

    JLabel labelNumero = new JLabel("Número do Cartão:");
    JTextField campoNumero = new JTextField();
    JLabel labelNome = new JLabel("Nome do Titular:");
    JTextField campoNome = new JTextField();
    JLabel labelValidade = new JLabel("Data de Validade:");
    JTextField campoValidade = new JTextField();
    JLabel labelCodigo = new JLabel("Código de Segurança:");
    JTextField campoCodigo = new JTextField();
    JButton botaoConfirmar = new JButton("Confirmar");

    painelCartao.add(labelNumero);
    painelCartao.add(campoNumero);
    painelCartao.add(labelNome);
    painelCartao.add(campoNome);
    painelCartao.add(labelValidade);
    painelCartao.add(campoValidade);
    painelCartao.add(labelCodigo);
    painelCartao.add(campoCodigo);
    painelCartao.add(new JLabel());
    painelCartao.add(botaoConfirmar);

    JLabel labelPix = new JLabel();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.weighty = 0.5;
    setorConteudo.add(labelPix, gbc);

    painelCartao.setVisible(false);
    labelPix.setVisible(false);

    botaoCredito.addActionListener(e -> {
        painelCartao.setVisible(true);
        labelPix.setVisible(false);
    });

    botaoDebito.addActionListener(e -> {
        painelCartao.setVisible(true);
        labelPix.setVisible(false);
    });

    botaoPix.addActionListener(e -> {
    painelCartao.setVisible(false);
    labelPix.setText("Codigo PIX: " + String.format("%020d", (long) (Math.random() * Math.pow(10, 20))));
    this.numero = labelPix.getText();
    labelPix.setVisible(true);
});

    botaoConfirmar.addActionListener(e -> {
        String numero = campoNumero.getText().trim();
        this.numero = numero;
        String nome = campoNome.getText().trim();
        String validade = campoValidade.getText().trim();
        String codigo = campoCodigo.getText().trim();

        if (numero.isEmpty() || nome.isEmpty() || validade.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (numero.length() != 16) {
            JOptionPane.showMessageDialog(this, "Número do cartão deve ter 16 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Processar os dados do cartão
        JOptionPane.showMessageDialog(this, "Dados do cartão confirmados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        painelCartao.setVisible(false);
    });

    JButton botaoPagar = new JButton("Pagar");
    botaoPagar.setFont(new Font("Arial", Font.BOLD, 15));
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.weighty = 0.1;
    setorConteudo.add(botaoPagar, gbc);

    botaoPagar.addActionListener(new ActionListener() {
        String numero = this.numero;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(this.numero == null){
                TemplateView telaFinalizacao = new FinalizacaoView("Finalização", Global.getPessoa(),"PIX");
                telaFinalizacao.setVisible(true);
                dispose();
            }
            else{
                TemplateView telaFinalizacao = new FinalizacaoView("Finalização", Global.getPessoa(), Integer.valueOf(numero.substring(11, 15)));
                telaFinalizacao.setVisible(true);
                dispose();
            }
        }
    });

    JButton botaoSair = new JButton("Sair");
    botaoSair.setFont(new Font("Arial", Font.BOLD, 15));
    adicionarAoRodape(botaoSair);

    botaoSair.addActionListener(e -> dispose());

    botaoVoltar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TemplateView telaCarrinho = new CarrinhoView("Carrinho", info);
            telaCarrinho.setVisible(true);
            dispose();
        }
    });
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarteiraView carteiraView = new CarteiraView();
            carteiraView.setSize(800, 600);
            carteiraView.setLocationRelativeTo(null);
            carteiraView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            carteiraView.setVisible(true);
        });
    }
}
