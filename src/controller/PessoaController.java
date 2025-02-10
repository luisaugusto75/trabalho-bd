package controller;

import model.*;

import java.sql.ResultSet;
import java.util.List;

import static model.Global.database;

/**
 * Essa classe é responsável por intermediar a interface gráfica com o modelo de Pessoa.
 * @see Pessoa
 */
public class PessoaController {

    /**
     * Construtor da classe PessoaController.
     */
    public PessoaController() {
    }
    /**
     * Busca uma pessoa no banco de dados pelo login.
     *
     * @param login O login da pessoa a ser buscada.
     * @return A pessoa encontrada, que pode ser um Cliente, Vendedor ou Administrador.
     */
    public Pessoa buscaPessoa(String login) {
        try {
            List listUsuario = database.GetPessoaByLogin(login);

            if (listUsuario.size() > 0) {
                if (listUsuario.get(0) instanceof Vendedor) {
                    return (Vendedor) listUsuario.get(0);
                } else if (listUsuario.get(0) instanceof Administrador) {
                    return (Administrador) listUsuario.get(0);
                }
                return (Cliente) listUsuario.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Futuramente, deve-se complementar este método para que ele discrimine a pessoa buscada no banco de dados entre cliente e vendedor, criando o objeto em questão com base nessa informação.
        Pessoa pessoa = new Cliente();
        return pessoa;
    }
}