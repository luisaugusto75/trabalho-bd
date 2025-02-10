package controller;

import model.Global;
import model.Helper;
import model.Produto;
import model.Vendedor;

import java.sql.ResultSet;
import java.util.List;

import static model.Global.database;

/**
 * Classe controladora para gerenciar as operações do vendedor.
 */
public class VendedorController {
    public Vendedor vendedor;

    /**
     * Construtor da classe VendedorController.
     * Inicializa o vendedor com a pessoa global.
     */
    public VendedorController(){
        this.vendedor = (Vendedor) Global.getPessoa();
    }

    public static List<Vendedor> pesquisarVendedores(String query) {
        try {
            List<Vendedor> listaVendedores = database.getVendedoresByQuery(query);
            return listaVendedores;
            /////// TODO: 06/06/2021 Implementar a lógica de pesquisa de vendedores

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void atualizarVendedor(Vendedor vendedor) {
        try {
            database.atualizarVendedor(vendedor);
        } catch (Exception e) {
            e.printStackTrace();
    }
    }

    /**
     * Insere um produto no banco de dados.
     *
     * @param produto O produto a ser inserido.
     * @return 1 se o produto foi inserido com sucesso, 0 caso contrário.
     */
    public int inserirProduto(Produto produto){
        try{
            database.inserirProduto(produto, vendedor);
            System.out.println("Produto inserido com sucesso");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}