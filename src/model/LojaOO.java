/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import controller.Tabela;
import model.Global;
import controller.DatabaseController;
import view.TelaInicialView;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe principal da aplicação LojaOO.
 * Responsável por iniciar a aplicação e exibir a tela inicial.
 *
 * @autor Pedro Maia
 */
public class LojaOO {

    /**
     * Método principal da aplicação.
     *
     * @param args os argumentos da linha de comando
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados
     */
    public static void main(String[] args) throws SQLException {
        TelaInicialView telaInicialView = new TelaInicialView("S.I.S.T.E.M.O",false);
        telaInicialView.show();
        DatabaseController dbCtrol = Global.getDatabase();
        ResultSet rs = dbCtrol.consulta(Tabela.pessoa,1);
        while (rs.next()) {
            System.out.println(rs.getInt("ID"));
        }
    }
}