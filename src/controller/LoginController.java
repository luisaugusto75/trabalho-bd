package controller;

import model.Pessoa;

import java.sql.ResultSet;

import static model.Global.database;

/**
 * Essa classe é responsável por intermediar a interface gráfica com o modelo de Login.
 * Gerencia a autenticação de um usuário.
 * Requisita o acesso ao Banco de Dados
 * @see Pessoa
 *
 */
public class LoginController {
    String senha;
    String login;


    public LoginController(String login, String senha)
    {
        this.login = login;
        this.senha = senha;
    }

    /**
     * Este método realiza uma ação importante.
     *
     * @param login login do usuário
     * @param senha senha do usuário
     * @return True se a autenticação for aceita e false se não.
     */
    public boolean autenticar(String login,String senha){
        // requisicao ao banco de dados com os parametros login e senha
        if (requisitaDB(login, senha))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Este método realiza uma ação importante.
     *
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @return retorna true ou false a depender do resultado da requisição feita pelo controlador do Banco de Dados
     * @see DatabaseController
     */
    public boolean requisitaDB(String login, String senha){
            // Agora o metodo requisita uma linha ao banco de dados e se houver uma resposta diferente de null retorna true
         try{
             ResultSet resultado =  database.autenticar(login, senha);
             if(resultado != null){
                 return true;
             }
             else{
                 return false;
             }
         }catch (Exception e){
             return false;
         }
    }
}
