package model;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.sql.Date;

/**
 * Classe que representa um Cliente, que é uma Pessoa com login, senha, telefone,
 * carteira de métodos de pagamento e um carrinho de compras.
 */
public class Cliente extends Pessoa{

    private String login;
    private String senha;

    private FileInputStream imagemPerfil;
    private boolean possuiCartao;
    private ArrayList<String> carteira;
    private String telefone;
    private Carrinho carrinho = new Carrinho(this);

    /**
     * Construtor da classe Cliente.
     *
     * @param nome O nome do cliente.
     * @param CPF O CPF do cliente.
     * @param nascimento A data de nascimento do cliente.
     * @param login O login do cliente.
     * @param senha A senha do cliente.
     * @param possuiCartao Se o cliente possui cartão.
     * @param carteira A carteira de métodos de pagamento do cliente.
     */
    public Cliente(String nome, String CPF, Date nascimento, String login, String senha, boolean possuiCartao, ArrayList<String> carteira, FileInputStream imagem) {
        super(nome, CPF, nascimento);
        this.login = login;
        this.senha = senha;
        this.possuiCartao = possuiCartao;
        this.carteira = carteira;
        this.imagemPerfil = imagem;
    }

    /**
     * Segundo construtor da classe Cliente.
     *
     * @param nome O nome do cliente.
     * @param CPF O CPF do cliente.
     * @param login O login do cliente.
     * @param senha A senha do cliente.
     * @param nascimento A data de nascimento do cliente.
     */
    public Cliente(String nome, String CPF, String login, String senha, Date nascimento,FileInputStream imagem){
        super(nome, CPF, nascimento);
        System.out.println("Nome: " + nome + "\nCPF: " + CPF);
        this.imagemPerfil = imagem;
        this.login = login;
        this.possuiCartao = false;
        this.senha = senha;
        this.carteira = new ArrayList<String>();
    }

    /**
     * Construtor padrão da classe Cliente.
     */
    public Cliente(){

    }

    /**
     * Retorna o carrinho do cliente.
     *
     * @return O carrinho do cliente.
     */
    public Carrinho getCarrinho() {
        return carrinho;
    }

    /**
     * Retorna o login do cliente.
     *
     * @return O login do cliente.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retorna a senha do cliente.
     *
     * @return A senha do cliente.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define o login do cliente.
     *
     * @param login O novo login do cliente.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna se o cliente possui cartão.
     *
     * @return true se o cliente possui cartão, false caso contrário.
     */
    public boolean getPossuiCartao() {
        return possuiCartao;
    }

    /**
     * Define se o cliente possui cartão.
     *
     * @param possuiCartao true se o cliente possui cartão, false caso contrário.
     */
    public void setPossuiCartao(boolean possuiCartao) {
        this.possuiCartao = possuiCartao;
    }

    /**
     * Define a senha do cliente.
     *
     * @param senha A nova senha do cliente.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Define o telefone do cliente.
     *
     * @param telefone O novo telefone do cliente.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o telefone do cliente.
     *
     * @return O telefone do cliente.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Retorna a carteira de métodos de pagamento do cliente.
     *
     * @return A carteira de métodos de pagamento do cliente.
     */
    public ArrayList<String> getCarteira() {
        return carteira;
    }

    /**
     * Adiciona um método de pagamento à carteira do cliente.
     *
     * @param MetodoPagamento O método de pagamento a ser adicionado.
     * @return 1 se o método de pagamento foi adicionado com sucesso, 0 se o método de pagamento já estava na carteira.
     */
    public int adicionaPagamento(String MetodoPagamento){
        if(this.carteira.contains(MetodoPagamento)){
            // Tratamento de erro na interface do usuário
            System.out.println("Metodo de pagamento já cadastrado");
            return 0;
        } else {
            this.carteira.add(MetodoPagamento);
            return 1;
        }
    }

    /**
     * Adiciona um cartão ao cliente.
     *
     * @return 1 se o cartão foi adicionado com sucesso, 0 se o cliente já possui cartão.
     */
    public int adicionarCartao(){
        if(this.possuiCartao){
            // Tratamento de erro na interface do usuário
            System.out.println("Cliente já possui cartão cadastrado");
            return 0;
        } else {
            this.possuiCartao = true;
            return 1;
        }
    }

    public FileInputStream getImagemPerfil(){
        return imagemPerfil;
    }

}