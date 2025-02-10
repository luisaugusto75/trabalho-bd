package model;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

/**
 * Classe utilitária para conversão de ResultSet em listas de objetos.
 */
public class Helper {

    /**
     * Converte um ResultSet em uma lista de produtos.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Uma lista de produtos.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */
    public static List<Produto> converterProdutos(ResultSet rs) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        while (rs.next()) {
            Produto produto;
            FileInputStream fis = null;
            try {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                float preco = rs.getFloat("Preco");
                int estoque = rs.getInt("Estoque");
                try {

                    InputStream inputStream = rs.getBinaryStream("imagem");
                    if(inputStream != null) {
                        File tempFile = File.createTempFile("imagem", ".tmp");
                        FileOutputStream fos = new FileOutputStream(tempFile);
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                        fos.close();
                        inputStream.close();
                        fis = new FileInputStream(tempFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String descricao = rs.getString("Descricao");


                produto = new Produto(id, nome, preco, estoque, descricao,fis, "");
                produtos.add(produto);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return produtos;
    }

    /**
     * Converte um ResultSet em um mapa de produtos e suas quantidades.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Um mapa de produtos e suas quantidades.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */
    public static HashMap<Produto, Integer> converterProdutosCarrinho(ResultSet rs) throws SQLException {
        HashMap<Produto, Integer> produtos = new HashMap<>();
        while (rs.next()) {
            Produto produto;
            try {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                float preco = rs.getFloat("Preco");
                int estoque = rs.getInt("Estoque");
                String descricao = rs.getString("Descricao");
                int qtd = rs.getInt("Quantidade");

                produto = new Produto(id, nome, preco, estoque, descricao, "");
                produtos.put(produto, qtd);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return produtos;
    }

    /**
     * Converte um ResultSet em uma lista de clientes.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Uma lista de clientes.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */
    public static List<Cliente> converterClientes(ResultSet rs) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente;
            try {
                String cpf = rs.getString("CPF");
                java.sql.Date nascimento;
                try {
                    nascimento = rs.getDate("Data_Nascimento");
                } catch (SQLException e) {
                    throw new RuntimeException("Error parsing date for CPF: " + rs.getString("CPF"), e);
                }
                String nome = rs.getString("Nome");
                String senha = rs.getString("Senha");
                String email = rs.getString("Email");

                cliente = new Cliente(nome, cpf, senha, email, nascimento, converterImagem(rs.getBlob("Imagem_Perfil")));
                clientes.add(cliente);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return clientes;
    }
    public static FileInputStream converterImagem(Blob blob){
        FileInputStream fis = null;
        try {
            InputStream inputStream = blob.getBinaryStream();
            if(inputStream != null) {
                File tempFile = File.createTempFile("imagem", ".tmp");
                FileOutputStream fos = new FileOutputStream(tempFile);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
                inputStream.close();
                fis = new FileInputStream(tempFile);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return fis;
    }

    public static ImageIcon fisToImageIcon(FileInputStream fis){
        if(fis == null) return null;
        try {
            // Criar um FileInputStream a partir de um arquivo de imagem

            // Converter FileInputStream para um array de bytes
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] dados = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(dados)) != -1) {
                buffer.write(dados, 0, bytesRead);
            }
            fis.close();

            // Criar um ImageIcon a partir dos bytes
            ImageIcon imageIcon = new ImageIcon(buffer.toByteArray());

            // Redimensionar a imagem (opcional)
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            ImageIcon resizedIcon = new ImageIcon(image);
            return resizedIcon;
        }
        catch (IOException e) {}
    return null;
    }


            /**
             * Converte um ResultSet em uma lista de vendedores.
             *
             * @param rs O ResultSet a ser convertido.
             * @return Uma lista de vendedores.
             * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
             */


    public static List<Vendedor> converterVendedores(ResultSet rs) throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        while (rs.next()) {
            Vendedor vendedor;
            System.out.println(rs.toString());
            try {
                String cpf = rs.getString("CPF");
                java.sql.Date nascimento;
                try {
                    nascimento = rs.getDate("Data_Nascimento");
                } catch (SQLException e) {
                    throw new RuntimeException("Error parsing date for CPF: " + rs.getString("CPF"), e);
                }
                String nome = rs.getString("Nome");
                String senha = rs.getString("Senha");
                String email = rs.getString("Email");
                float salario = rs.getFloat("Salario");
                float comissao = rs.getFloat("Comissao");
                //public Vendedor(String nome, String CPF, String login, String senha, Date nascimento, Double salario, double comissao) {

                vendedor = new Vendedor(nome, cpf, email,senha, nascimento, Double.valueOf(salario), comissao);
                vendedores.add(vendedor);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return vendedores;
    }
}