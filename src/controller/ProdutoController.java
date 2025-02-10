package controller;
import model.*;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.List;

import static model.Global.database;
public class ProdutoController {

    public ProdutoController(){

    }

    public ResultSet buscaProdutos() {
        try {
            ResultSet listProdutos = database.consulta(Tabela.produto);
            return listProdutos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void atualizarProduto(Produto produto){
        try{
            database.atualizarProduto(produto);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
