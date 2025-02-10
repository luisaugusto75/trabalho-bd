package model;

import controller.DatabaseController;

import java.sql.SQLException;

/**
 * Classe que representa o estado global da aplicação.
 */
public class Global {
    public static Pessoa pessoa;

    /**
     * Retorna a pessoa global.
     *
     * @return A pessoa global.
     */
    public static Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * Define a pessoa global.
     *
     * @param _pessoa A nova pessoa global.
     */
    public static void setPessoa(Pessoa _pessoa) {
        System.out.println("Pessoa Global setada\nNome:" + _pessoa.getNome());
        pessoa = _pessoa;
    }

    public static DatabaseController database;

    static {
        try {
            database = new DatabaseController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna o cliente global.
     *
     * @return O cliente global.
     */
    public static Cliente getCliente() {
        return (Cliente) pessoa;
    }

    /**
     * Retorna o controlador de banco de dados global.
     *
     * @return O controlador de banco de dados global.
     */
    public static DatabaseController getDatabase() {
        return database;
    }
}