package view;

import controller.VendaController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class FinalizacaoView extends TemplateView {
    private JComboBox<String> vendedorComboBox;
    private JLabel valorFinalLabel;
    private VendaController vendaController;

    private String codigoPix;
    private int digitosCartao = 0;
    private List<Vendedor> vendedores;

    public FinalizacaoView(String titulo, Pessoa pessoa, int digitosCartao) {
        super(titulo);
        this.digitosCartao = digitosCartao;
        initUI();
    }

    public FinalizacaoView(String titulo, Pessoa pessoa, String pagamentoPix) {
        super(titulo);
        this.codigoPix = pagamentoPix;
        initUI();
    }

    private void initUI() {
        vendaController = new VendaController();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Painel principal para organizar os componentes
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel vendedorLabel = new JLabel("Vendedor:");
        vendedorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(vendedorLabel, gbc);

        vendedorComboBox = new JComboBox<>();
        carregarVendedores();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(vendedorComboBox, gbc);

        JLabel valorLabel = new JLabel("Valor Final:");
        valorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(valorLabel, gbc);

        valorFinalLabel = new JLabel(String.format("R$ %.2f", vendaController.obterValorFinal(Global.pessoa.getCPF())));
        valorFinalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(valorFinalLabel, gbc);

        JButton finalizarButton = new JButton("Finalizar Compra");
        finalizarButton.setFont(new Font("Arial", Font.BOLD, 14));
        finalizarButton.setBackground(new Color(50, 150, 250));
        finalizarButton.setForeground(Color.WHITE);
        finalizarButton.setFocusPainted(false);
        finalizarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(finalizarButton, gbc);

        add(panel, gbc);
    }

    private void carregarVendedores() {
        try {
            vendedores = Global.database.getVendedores();
            for (Vendedor vendedor : vendedores) {
                vendedorComboBox.addItem(vendedor.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finalizarCompra() {
        try {
            List<Vendedor> selectedVendedores = Global.database.getVendedoresByQuery((String) vendedorComboBox.getSelectedItem());
            if (selectedVendedores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vendedor não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Vendedor vendedor = selectedVendedores.get(0);
            double valorFinal = vendaController.obterValorFinal(Global.pessoa.getCPF());

            Venda vendaAtual = vendaController.cadastrarVenda(vendedor, digitosCartao, new Date());
            Global.database.limparCarrinhoByCpf(Global.pessoa.getCPF());

            String recibo = this.digitosCartao != 0 ?
                    String.format("Recibo\n\nVendedor: %s\nValor Final: R$ %.2f\nData: %s\nCartão final: %d\nID da Venda: %d",
                            vendaAtual.getVendedor().getNome(), vendaAtual.getValor(), vendaAtual.getData(), digitosCartao) :
                    String.format("Recibo\n\nVendedor: %s\nValor Final: R$ %.2f\nData: %s\nPagamento por PIX\nCódigo PIX: %s",
                            vendaAtual.getVendedor().getNome(), vendaAtual.getValor(), vendaAtual.getData(), codigoPix);

            JOptionPane.showMessageDialog(this, recibo, "Recibo", JOptionPane.INFORMATION_MESSAGE);
            salvarRecibo(recibo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarRecibo(String recibo) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Recibo");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileToSave)) {
                    writer.write(recibo);
                }
                JOptionPane.showMessageDialog(this, "Recibo salvo com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar recibo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
