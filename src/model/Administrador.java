package model;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Classe que representa um Administrador, que é uma Pessoa com salário, login, senha,
 * e listas de clientes e vendedores.
 */
public class Administrador extends Pessoa {

    private float salario;
    private String login;
    private String senha;
    private ArrayList<Cliente> clientes;
    private ArrayList<Vendedor> vendedores;

    /**
     * Construtor da classe Administrador.
     *
     * @param salario O salário do administrador.
     * @param nome O nome do administrador.
     * @param CPF O CPF do administrador.
     * @param nascimento A data de nascimento do administrador.
     * @param login O login do administrador.
     * @param senha A senha do administrador.
     */
    public Administrador(float salario, String nome, String CPF, Date nascimento, String login, String senha) {
        super(nome, CPF, nascimento);
        this.login = login;
        this.senha = senha;
        this.salario = salario;
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    /**
     * Retorna o salário do administrador.
     *
     * @return O salário do administrador.
     */
    public float getSalario() {
        return salario;
    }

    /**
     * Define o salário do administrador.
     *
     * @param salario O novo salário do administrador.
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * Retorna uma representação em string do administrador.
     *
     * @return Uma string representando o administrador.
     */
    public String toString() {
        return "Administrador{" +
                "salario=" + salario +
                ", clientes=" + clientes +
                ", vendedores=" + vendedores +
                '}';
    }

    /**
     * Cadastra um novo cliente.
     *
     * @param nome O nome do cliente.
     * @param CPF O CPF do cliente.
     * @param nascimento A data de nascimento do cliente.
     * @param login O login do cliente.
     * @param senha A senha do cliente.
     * @param possuiCartao Se o cliente possui cartão.
     * @return 1 se o cliente foi cadastrado com sucesso, 0 caso contrário.
     */
    public int cadastraCliente(String nome, String CPF, Date nascimento, String login, String senha, boolean possuiCartao, FileInputStream fis) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(CPF)) {
                System.out.println("Cliente já cadastrado.");
                return 0;
            }
        }
        Cliente novoCliente = new Cliente(nome, CPF, nascimento, login, senha, possuiCartao, new ArrayList<>(),fis);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado.");
        return 1;
    }

    /**
     * Cadastra um novo vendedor.
     *
     * @param nome O nome do vendedor.
     * @param CPF O CPF do vendedor.
     * @param login O login do vendedor.
     * @param senha A senha do vendedor.
     * @param nascimento A data de nascimento do vendedor.
     * @param salario O salário do vendedor.
     * @param comissao A comissão do vendedor.
     * @return 1 se o vendedor foi cadastrado com sucesso, 0 caso contrário.
     */
    public int cadastraVendedor(String nome, String CPF, String login, String senha, Date nascimento, Double salario, double comissao) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCPF().equals(CPF)) {
                System.out.println("Vendedor já cadastrado.");
                return 0;
            }
        }
        Vendedor novoVendedor = new Vendedor(nome, CPF, login, senha, nascimento, salario, comissao);
        vendedores.add(novoVendedor);
        System.out.println("Vendedor cadastrado.");
        return 1;
    }

    /**
     * Deleta um cliente pelo CPF.
     *
     * @param CPF O CPF do cliente a ser deletado.
     * @return 1 se o cliente foi deletado com sucesso, 0 caso contrário.
     */
    public int deletaCliente(String CPF) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(CPF)) {
                clientes.remove(cliente);
                System.out.println("Cliente removido.");
                return 1;
            }
        }
        System.out.println("Cliente não encontrado.");
        return 0;
    }

    /**
     * Deleta um vendedor pelo CPF.
     *
     * @param CPF O CPF do vendedor a ser deletado.
     * @return 1 se o vendedor foi deletado com sucesso, 0 caso contrário.
     */
    public int deletaVendedor(String CPF) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCPF().equals(CPF)) {
                vendedores.remove(vendedor);
                System.out.println("Vendedor removido.");
                return 1;
            }
        }
        System.out.println("Vendedor não encontrado.");
        return 0;
    }
}