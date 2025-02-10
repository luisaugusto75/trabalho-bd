package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FimCadastroView extends JPanel {
    public FimCadastroView(String titulo) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel mensagemSucesso = new JLabel("Cadastro realizado com sucesso!");
        mensagemSucesso.setFont(new Font("Arial", Font.BOLD, 18));
        mensagemSucesso.setHorizontalAlignment(JLabel.CENTER);
        JButton botaoMenu = new JButton("Menu");
        botaoMenu.setPreferredSize(new Dimension(200, 50));
        botaoMenu.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(mensagemSucesso, gbc);
        gbc.gridy++;
        add(botaoMenu, gbc);

        botaoMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial",false);
                telaInicial.setVisible(true);
                JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(FimCadastroView.this);
                janela.dispose();
            }
        });
    }
}
