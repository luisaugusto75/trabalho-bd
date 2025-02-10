package controller;

import model.*;

import java.util.Date;
import java.util.Map;


public class VendaController {
    Map<Produto,Integer> produtos = Global.getCliente().getCarrinho().getConteudo();
    int quantidade = Global.getCliente().getCarrinho().getQuantidade();
    Cliente cliente = Global.getCliente();
    float valor = obterValorFinal(cliente.getCPF());

    public Venda cadastrarVenda(Vendedor vendedor, int digitosCartao,Date data) {
        Venda venda = new Venda(produtos,vendedor,quantidade, cliente, valor, data);
        venda.setDigitosCartao(digitosCartao);
        return venda;
    }

    public float  obterValorFinal(String cpf) {
        try {
            return Global.database.getValorTotal(cpf);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }
}
