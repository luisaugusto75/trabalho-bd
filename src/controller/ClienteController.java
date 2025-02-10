package controller;

import model.Cliente;
import model.Global;

import java.io.FileInputStream;
import java.sql.Date;
import java.sql.SQLException;
import controller.Tabela;

/**
 * Essa classe é responsável por intermediar a interface gráfica com o modelo de Cliente.
 * Gerencia a criação de um novo cliente.
 * @see Cliente
 *
 */
public class ClienteController {

    /**
     * Este método realiza a ação de criar um novo cliente.
     * @param nome Nome do cliente
     * @param cpf CPF do cliente
     * @param email Email do cliente
     * @param senha Senha do cliente
     * @param nascimento Data de nascimento do cliente
     * @throws SQLException
     */
    public void criaCliente(String nome , String cpf, String email, String senha, Date nascimento, FileInputStream fis) throws SQLException {
        Cliente clienteDTO = new Cliente(nome,cpf,email,senha,nascimento,fis);
        Global.setPessoa(clienteDTO);
        System.out.println("Global.pessoa: "+Global.getPessoa().getNome());
        try{
            Global.database.cadastrar(clienteDTO);
        }catch (SQLException e){
            System.out.println("Erro ao inserir cliente no banco de dados");
        }

    };

}
