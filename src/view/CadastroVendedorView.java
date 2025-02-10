package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import model.Vendedor;

/**
 * Classe que representa a interface gráfica para cadastro de vendedores.
 */
public class CadastroVendedorView extends TemplateView {
    private JLabel lblNome, lblCPF, lblLogin, lblSenha, lblSalario, lblNascimento, lblComissao;
    private JTextField txtNome, txtCPF, txtLogin, txtSalario, txtNascimento, txtComissao;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    /**
     * Construtor da classe CadastroVendedorView.
     */
    public CadastroVendedorView() {
        super("Cadastro de Vendedor");
        initializeComponents();
        setupLayout();
    }

    /**
     * Inicializa os componentes da interface gráfica.
     */
    private void initializeComponents() {
        lblNome = new JLabel("Nome:");
        lblCPF = new JLabel("CPF:");
        lblLogin = new JLabel("Login:");
        lblSenha = new JLabel("Senha:");
        lblSalario = new JLabel("Salário:");
        lblComissao = new JLabel("Comissão");
        lblNascimento = new JLabel("Data de Nascimento");

        txtNome = new JTextField();
        txtCPF = new JTextField();
        txtLogin = new JTextField();
        txtSenha = new JPasswordField();
        txtSalario = new JTextField();
        txtComissao = new JTextField();
        txtNascimento = new JTextField();

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarVendedor());

        setorConteudo.setLayout(null);
    }

    /**
     * Configura o layout da interface gráfica.
     */
    private void setupLayout() {
        int x = 100, y = 70, largura = 400, altura = 30, espacamento = 20, x2 = 250;

        lblNome.setBounds(x, y, largura, altura);
        txtNome.setBounds(x2, y, largura, altura);

        lblCPF.setBounds(x, y + espacamento * 2, largura, altura);
        txtCPF.setBounds(x2, y + espacamento * 2, largura, altura);

        lblNascimento.setBounds(x, y + espacamento * 4, largura, altura);
        txtNascimento.setBounds(x2, y + espacamento * 4, largura, altura);

        lblLogin.setBounds(x, y + espacamento * 6, largura, altura);
        txtLogin.setBounds(x2, y + espacamento * 6, largura, altura);

        lblSenha.setBounds(x, y + espacamento * 8, largura, altura);
        txtSenha.setBounds(x2, y + espacamento * 8, largura, altura);

        lblSalario.setBounds(x, y + espacamento * 10, largura, altura);
        txtSalario.setBounds(x2, y + espacamento * 10, largura, altura);

        lblComissao.setBounds(x, y + espacamento * 12, largura, altura);
        txtComissao.setBounds(x2, y + espacamento * 12, largura, altura);

        btnCadastrar.setBounds(400, y + espacamento * 18, largura / 3, altura);

        setorConteudo.add(lblNome);
        setorConteudo.add(txtNome);
        setorConteudo.add(lblCPF);
        setorConteudo.add(txtCPF);
        setorConteudo.add(lblLogin);
        setorConteudo.add(txtLogin);
        setorConteudo.add(lblSenha);
        setorConteudo.add(txtSenha);
        setorConteudo.add(lblSalario);
        setorConteudo.add(txtSalario);
        setorConteudo.add(lblComissao);
        setorConteudo.add(txtComissao);
        setorConteudo.add(lblNascimento);
        setorConteudo.add(txtNascimento);
        setorConteudo.add(btnCadastrar);

        JButton sair = new JButton("Sair");
        adicionarAoRodape(btnCadastrar);
        adicionarAoRodape(sair);
    }

    /**
     * Realiza o cadastro do vendedor.
     */
    private void cadastrarVendedor() {
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());
        double salario = Double.parseDouble(txtSalario.getText());
        Date nascimento = Date.valueOf(txtNascimento.getText());
        double comissao = Double.parseDouble(txtComissao.getText());

        if (nome.length() > 50) {
            JOptionPane.showMessageDialog(this, "Nome do produto deve ter no máximo 50 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (login.length() > 50) {
            JOptionPane.showMessageDialog(this, "Login do vendedor deve ter no máximo 50 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (senha.length() > 50) {
            JOptionPane.showMessageDialog(this, "Senha do vendedor deve ter no máximo 50 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (cpf.length() > 11) {
            JOptionPane.showMessageDialog(this, "CPF do vendedor deve ter no máximo 11 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (salario < 0) {
            JOptionPane.showMessageDialog(this, "Salário do vendedor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (comissao < 0) {
            JOptionPane.showMessageDialog(this, "Comissão do vendedor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (nascimento.after(new Date(System.currentTimeMillis()))) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (nascimento.before(new Date(System.currentTimeMillis() - 31556952000L))) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vendedor vendedor = new Vendedor(nome, cpf, login, senha, nascimento, salario, comissao);
        JOptionPane.showMessageDialog(this, "Vendedor cadastrado com sucesso!");
    }

    /**
     * Método principal para iniciar a interface gráfica.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        TemplateView tela = new CadastroVendedorView();
        tela.setVisible(true);
    }
}