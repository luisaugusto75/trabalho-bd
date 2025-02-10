package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class TelaInicialView extends TemplateView {
    private JButton fazerLogin;
    private JButton cadastrar;
    private boolean usuarioLogado;
    private Pessoa usuarioAtual;

    public TelaInicialView(String titulo, boolean usuarioLogado) {
        super(titulo);
        this.usuarioLogado = usuarioLogado;

        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("<html>Bem vindo ao S.I.S.T.E.M.O- Solução Inovadora para Suportar Transações Eficientes no Mercado Online</html>");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new Dimension(400, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaInicial.add(header, gbc);

        if (usuarioLogado) {
            // Opções disponíveis apenas para usuários logados
            JButton verProdutos = new JButton("Ver produtos");
            ajustarBotao(verProdutos, gbc, telaInicial);

            JButton verCarrinho = new JButton("Ver carrinho");
            ajustarBotao(verCarrinho, gbc, telaInicial);

            verProdutos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView listaProdutos = new ListaProdutosView("Lista de Produtos");
                    listaProdutos.setVisible(true);
                    dispose();
                }
            });

            verCarrinho.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView carrinhoView = new CarrinhoView("Carrinho", new HashMap<>());//adicionei o new hashmap so pra compilar (inacio)
                    carrinhoView.setVisible(true);
                    dispose();
                }
            });

        } else {
            // Opções disponíveis apenas para usuários não logados
            fazerLogin = new JButton("Fazer login");
            cadastrar = new JButton("Cadastrar");

            ajustarBotao(fazerLogin, gbc, telaInicial);
            ajustarBotao(cadastrar, gbc, telaInicial);

            // Adiciona uma mensagem informativa
            JLabel mensagemLogin = new JLabel("<html>Faça login ou cadastre-se para acessar nossos produtos!</html>");
            mensagemLogin.setHorizontalAlignment(JLabel.CENTER);
            gbc.gridy++;
            telaInicial.add(mensagemLogin, gbc);

            cadastrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView telaCadastroCliente = new cadastroClienteView("Cadastro de Cliente");
                    telaCadastroCliente.setVisible(true);
                    dispose();
                }
            });

            fazerLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LoginView telaLogin = new LoginView("Login");
                    telaLogin.setVisible(true);
                    dispose();
                }
            });
        }

        adicionarConteudo(telaInicial);

        // Botões do rodapé
        JButton ajuda = new JButton("Ajuda");
        JButton sair = new JButton("Sair");
        adicionarAoRodape(ajuda);
        adicionarAoRodape(sair);
    }
    public TelaInicialView(String titulo, boolean usuarioLogado, Pessoa usuarioAtual) {
        super(titulo);
        this.usuarioLogado = usuarioLogado;
        this.usuarioAtual = usuarioAtual;
        // aqui precisamos da lógica para criar a imagem do usuario na tela inicial quando ele está logado
        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("<html>Bem vindo ao S.I.S.T.E.M.O- Solução Inovadora para Suportar Transações Eficientes no Mercado Online</html>");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new Dimension(400, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaInicial.add(header, gbc);

        if (usuarioLogado) {
            // Opções disponíveis apenas para usuários logados
            if(usuarioAtual instanceof Cliente){

            JButton verProdutos = new JButton("Ver produtos");
            ajustarBotao(verProdutos, gbc, telaInicial);

            JButton verCarrinho = new JButton("Ver carrinho");
            ajustarBotao(verCarrinho, gbc, telaInicial);

            JButton verPerfil = new JButton("Ver Perfil");
            ajustarBotao(verPerfil, gbc, telaInicial);

            verProdutos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView listaProdutos = new ListaProdutosView("Lista de Produtos");
                    listaProdutos.setVisible(true);
                    dispose();
                }
            });

            verCarrinho.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView carrinhoView = new CarrinhoView("Carrinho", new HashMap<>());//adicionei o new hashmap so pra compilar (inacio)
                    carrinhoView.setVisible(true);
                    dispose();
                }
            });
            verPerfil.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView perfilView = new PerfilView("Perfil", (Cliente) usuarioAtual);
                    perfilView.setVisible(true);
                    dispose();
                }
            });
            }else if (usuarioAtual instanceof Vendedor){
                JButton verProdutos = new JButton("Ver produtos");
                ajustarBotao(verProdutos, gbc, telaInicial);

                JButton adicionarProdutos = new JButton("Adicionar produtos");
                ajustarBotao(adicionarProdutos, gbc, telaInicial);

                JButton verPerfil = new JButton("Ver Perfil");
                ajustarBotao(verPerfil, gbc, telaInicial);

                adicionarProdutos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TemplateView adicionarProdutosView = new AdicionarProdutosView("Adicionar Produtos",(Vendedor)usuarioAtual);
                        adicionarProdutosView.setVisible(true);
                        dispose();
                    }
                });

                verProdutos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TemplateView listaProdutos = new ListaProdutosView("Lista de Produtos");
                        listaProdutos.setVisible(true);
                        dispose();
                    }
                });

                verPerfil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TemplateView perfilView = new PerfilView("Perfil", (Vendedor)usuarioAtual);
                        perfilView.setVisible(true);
                        dispose();
                    }
                });
            }else if( usuarioAtual instanceof Administrador){
                System.out.println("Administrador");

                JButton verProdutos = new JButton("Ver produtos");
                ajustarBotao(verProdutos, gbc, telaInicial);

                JButton cadastroFuncionario = new JButton("Cadastrar Funcionário");
                ajustarBotao(cadastroFuncionario, gbc, telaInicial);

                JButton gerenciarFuncionarios = new JButton("Gerenciar Funcionários");
                ajustarBotao(gerenciarFuncionarios, gbc, telaInicial);

                cadastroFuncionario.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TemplateView adicionarProdutosView = new AdicionarFuncionarioView("Adicionar Vendedores",(Administrador)usuarioAtual);
                        adicionarProdutosView.setVisible(true);
                        dispose();
                    }
                });

//                adicionarProdutos.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        TemplateView adicionarProdutosView = new AdicionarProdutosView("Adicionar Produtos",(Vendedor)usuarioAtual);
//                        adicionarProdutosView.setVisible(true);
//                        dispose();
//                    }
//                });

                verProdutos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TemplateView listaProdutos = new ListaProdutosView("Lista de Produtos");
                        listaProdutos.setVisible(true);
                        dispose();
                    }
                });

                gerenciarFuncionarios.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TemplateView gerenciarFuncionariosView = new GerenciarFuncionariosView("Gerenciar Funcionários",(Administrador)usuarioAtual);
                        gerenciarFuncionariosView.setVisible(true);
                        dispose();
                    }
                });




            }

        } else {
            // Opções disponíveis apenas para usuários não logados
            fazerLogin = new JButton("Fazer login");
            cadastrar = new JButton("Cadastrar");

            ajustarBotao(fazerLogin, gbc, telaInicial);
            ajustarBotao(cadastrar, gbc, telaInicial);

            // Adiciona uma mensagem informativa
            JLabel mensagemLogin = new JLabel("<html>Faça login ou cadastre-se para acessar nossos produtos!</html>");
            mensagemLogin.setHorizontalAlignment(JLabel.CENTER);
            gbc.gridy++;
            telaInicial.add(mensagemLogin, gbc);

            cadastrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView telaCadastroCliente = new cadastroClienteView("Cadastro de Cliente");
                    telaCadastroCliente.setVisible(true);
                    dispose();
                }
            });

            fazerLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView telaLogin = new LoginView("Login");
                    telaLogin.setVisible(true);
                    dispose();
                }
            });
        }

        adicionarConteudo(telaInicial);

        // Botões do rodapé
        JButton ajuda = new JButton("Ajuda");
        JButton sair = new JButton("Sair");

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView("Login");
                loginView.setVisible(true);
                dispose();
            }
        });
        adicionarAoRodape(ajuda);
        adicionarAoRodape(sair);
    }

    private void ajustarBotao(JButton botao, GridBagConstraints gbc, JPanel painel) {
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        gbc.gridwidth = 2;
        painel.add(botao, gbc);
    }


}