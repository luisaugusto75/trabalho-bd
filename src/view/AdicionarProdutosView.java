package view;

import controller.VendedorController;
import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AdicionarProdutosView extends TemplateView {

    private Vendedor vendedor;
    private JTextField campoNome, campoPreco, campoEstoque;
    private JTextArea campoDescricao;
    private JComboBox<String> campoCategoria;
    private JButton botaoPublicar, botaoImagem;
    private JLabel caracteresRestantes;
    private VendedorController vendedorController;
    private FileInputStream fis;
    private static final int MAX_DESCRICAO = 280;

    public AdicionarProdutosView(String titulo, Vendedor vendedor) {
        super(titulo);
        this.vendedor = vendedor;
        this.vendedorController = new VendedorController();

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel headerLabel = new JLabel("Novo Produto", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(headerLabel, gbc);

        gbc.gridy++;
        painelPrincipal.add(criarPainelCampo("Nome do Produto", campoNome = new JTextField()), gbc);

        gbc.gridy++;
        painelPrincipal.add(criarPainelCampo("Preço (R$)", campoPreco = new JTextField()), gbc);
        campoPreco.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        gbc.gridy++;
        painelPrincipal.add(criarPainelCampo("Quantidade em Estoque", campoEstoque = new JTextField()), gbc);
        campoEstoque.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        gbc.gridy++;
        campoCategoria = new JComboBox<>(new String[]{"Eletrônicos", "Roupas", "Acessórios", "Livros", "Casa", "Outros"});
        painelPrincipal.add(criarPainelCampo("Categoria", campoCategoria), gbc);

        gbc.gridy++;
        painelPrincipal.add(criarPainelDescricao(), gbc);

        gbc.gridy++;
        botaoImagem = new JButton("Adicionar Imagem");
        botaoImagem.setPreferredSize(new Dimension(350, 40));
        botaoImagem.addActionListener(e -> addImagem());
        painelPrincipal.add(botaoImagem, gbc);

        gbc.gridy++;
        botaoPublicar = new JButton("Publicar");
        botaoPublicar.setPreferredSize(new Dimension(120, 40));
        botaoPublicar.setBackground(new Color(29, 161, 242));
        botaoPublicar.setForeground(Color.WHITE);
        botaoPublicar.setBorderPainted(false);
        botaoPublicar.addActionListener(e -> publicarProduto());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.add(botaoPublicar);
        painelPrincipal.add(painelBotoes, gbc);

        adicionarConteudo(painelPrincipal);
    }

    private JPanel criarPainelCampo(String labelText, JComponent campo) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.WHITE);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        painel.add(label, BorderLayout.NORTH);
        painel.add(campo, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelDescricao() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Descrição do Produto");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        painel.add(label, BorderLayout.NORTH);

        campoDescricao = new JTextArea();
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(campoDescricao);
        scroll.setPreferredSize(new Dimension(350, 100));
        painel.add(scroll, BorderLayout.CENTER);

        caracteresRestantes = new JLabel(MAX_DESCRICAO + " caracteres restantes", SwingConstants.RIGHT);
        caracteresRestantes.setForeground(Color.GRAY);
        campoDescricao.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { atualizarContador(); }
            public void insertUpdate(DocumentEvent e) { atualizarContador(); }
            public void removeUpdate(DocumentEvent e) { atualizarContador(); }
        });
        painel.add(caracteresRestantes, BorderLayout.SOUTH);

        return painel;
    }
    @Override
    protected void aoVoltar(){
        TelaInicialView telaInicial = new TelaInicialView("Tela Inicial", true, vendedor);
        telaInicial.setVisible(true);
        dispose();
    }

    /**
     * Atualiza o contador de caracteres restantes na descrição.
     */
    private void atualizarContador() {
        int restantes = MAX_DESCRICAO - campoDescricao.getText().length();
        caracteresRestantes.setText(restantes + " caracteres restantes");
        caracteresRestantes.setForeground(restantes < 0 ? Color.RED : Color.GRAY);
        botaoPublicar.setEnabled(restantes >= 0);
    }

    /**
     * Publica o produto após validar os campos.
     */
    private void publicarProduto() {
        // Validação dos campos
        String nome = campoNome.getText().trim();
        String precoText = campoPreco.getText().trim();
        String estoqueText = campoEstoque.getText().trim();
        String descricao = campoDescricao.getText().trim();

        if (nome.isEmpty() || precoText.isEmpty() || estoqueText.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos são obrigatórios!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação do preço
        float preco;
        try {
            preco = Float.parseFloat(precoText);
            if (preco <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Preço deve ser um número válido e maior que zero!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação do estoque
        int estoque;
        try {
            estoque = Integer.parseInt(estoqueText);
            if (estoque < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Quantidade em estoque deve ser um número válido e não negativo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação da descrição
        if (descricao.length() > MAX_DESCRICAO) {
            JOptionPane.showMessageDialog(this,
                    "Descrição não pode exceder " + MAX_DESCRICAO + " caracteres!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Produto novoProduto = new Produto(
                nome,
                preco,
                estoque,
                descricao,
                fis,
                campoCategoria.getSelectedItem().toString()
        );

        vendedorController.inserirProduto(novoProduto);

        JOptionPane.showMessageDialog(this,
                "Produto publicado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        // Volta para a tela inicial
        new TelaInicialView("Início", true, vendedor).setVisible(true);
        dispose();
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