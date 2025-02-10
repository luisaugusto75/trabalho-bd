package controller;
import model.*;

import java.sql.*;
import java.io.File;
import java.sql.Date;
import java.util.*;
import java.io.*;

import static model.Global.database;


/**
 * Essa classe é responsável por intermediar a interface gráfica com o modelo de Banco de Dados.
 * Gerencia todas as interações com o Banco de dados e o Sistema
 * O principal intermediario dessa classe é a classe Global
 * @see Global
 *
 */
public class DatabaseController {
    Connection conn;

    /**
     * Este método realiza a ação de criar um novo cliente.
     * @throws SQLException
     */
    public DatabaseController() throws SQLException {
        String relativePath = "Database/BDLoja.db";
        String dbPath = new File(relativePath).getAbsolutePath();
        String url = "jdbc:sqlite:" + dbPath;
        conn = DriverManager.getConnection(url);

    }



    /**
     * Este método realiza uma ação importante.
     *
     * @param opcao Enum que possui as tabelas do banco de dados
     * @param id Descrição do segundo parâmetro
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
    public ResultSet consulta(Tabela opcao, int id) throws SQLException {
        String table;
        String idString = "ID";

        switch (opcao) {
            case carrinho:
                table =  "Carrinho";

                break;
            case categoria:
                table =  "Categoria";
                break;
            case cliente:
                table =  "Cliente";
                idString = "CPF";
                break;
            case item_carrinho:
                table =  "Item_Carrinho";
                break;
            case item_pedido:
                table =  "Item_Pedido";
                break;
            case pagamento:
                table =  "Pagamento";
                break;
            case pedido:
                table =  "Pedido";
                break;
            case pessoa:
                table =  "Pessoa";
                idString = "CPF";
                break;
            case produto:
                table =  "Produto";

                break;
            case vendedor:
                table =  "Vendedor";
                idString = "CPF";
                break;
            default:
                return null;

        }

        String sqlSelect = "SELECT * FROM "+table+" WHERE "+ idString + " = " +id;
        System.out.println(sqlSelect);
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);


        return stmt.executeQuery();
    }

    /**
     * Este método realiza uma ação importante.
     *
     * @param opcao Enum que possui as tabelas do banco de dados
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
    public ResultSet consulta(Tabela opcao, String id) throws SQLException {
        String table;
        String idString = "ID";

        switch (opcao) {
            case carrinho:
                table = "Carrinho";
                break;
            case categoria:
                table = "Categoria";
                break;
            case cliente:
                table = "Cliente";
                idString = "Email";
                break;
            case item_carrinho:
                table = "Item_Carrinho";
                break;
            case item_pedido:
                table = "Item_Pedido";
                break;
            case pagamento:
                table = "Pagamento";
                break;
            case pedido:
                table = "Pedido";
                break;
            case pessoa:
                table = "Pessoa";
                idString = "CPF";
                break;
            case produto:
                table = "Produto";
                break;
            case vendedor:
                table = "Vendedor";
                idString = "CPF";
                break;
            default:
                return null;
        }

        String sqlSelect = "SELECT * FROM " + table + " WHERE " + idString + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, id);
        System.out.println(sqlSelect);

        return stmt.executeQuery();
    }


    /**
     * Este método realiza uma ação importante.
     *
     * @param opcao Enum que possui as tabelas do banco de dados
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
    public ResultSet consulta(Tabela opcao) throws SQLException {
        String table;

        switch (opcao) {
            case carrinho:
                table =  "Carrinho";

                break;
            case categoria:
                table =  "Categoria";
                break;
            case cliente:
                table =  "Cliente";
                break;
            case item_carrinho:
                table =  "Item_Carrinho";
                break;
            case item_pedido:
                table =  "Item_Pedido";
                break;
            case pagamento:
                table =  "Pagamento";
                break;
            case pedido:
                table =  "Pedido";
                break;
            case pessoa:
                table =  "Pessoa";
                break;
            case produto:
                table =  "Produto";

                break;
            case vendedor:
                table =  "Vendedor";
                break;
            default:
                return null;

        }

        String sqlSelect = "SELECT * FROM "+table;
        System.out.println(sqlSelect);
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);


        return stmt.executeQuery();
    }

    /**
     * Método responsável por autenticar um usuário no sistema
     *
     *
     * @param login login do usuario
     * @param senha senha do usuario
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
    public ResultSet autenticar(String login, String senha) throws SQLException {
        // Primeiro tenta autenticar como cliente
        String sqlSelectCliente = "SELECT p.* FROM Pessoa p " +
                "JOIN Cliente c ON p.CPF = c.CPF " +
                "WHERE p.Email = ? AND p.Senha = ?";


        PreparedStatement stmtCliente = conn.prepareStatement(sqlSelectCliente);
        stmtCliente.setString(1, login);
        stmtCliente.setString(2, senha);
        ResultSet rsCliente = stmtCliente.executeQuery();

        if (rsCliente.isBeforeFirst()) {
            return rsCliente; // Retorna os dados do cliente se encontrado
        }

        // Se não encontrou como cliente, tenta como vendedor
        String sqlSelectVendedor = "SELECT * FROM Pessoa p " +
                "JOIN vendedor v On p.Cpf = v.Cpf" +
                " WHERE p.Email = ? AND p.Senha = ?";
        PreparedStatement stmtVendedor = conn.prepareStatement(sqlSelectVendedor);
        stmtVendedor.setString(1, login);
        stmtVendedor.setString(2, senha);
        ResultSet rsVendedor = stmtVendedor.executeQuery();

        if (rsVendedor.isBeforeFirst()) {
            return rsVendedor; // Retorna os dados do vendedor se encontrado
        }

        String sqlSelectAdmin = "SELECT * FROM Pessoa p " +
                "JOIN Administrador a On p.Cpf = a.Cpf" +
                " WHERE p.Email = ? AND p.Senha = ?";
        PreparedStatement stmtAdmin = conn.prepareStatement(sqlSelectAdmin);
        stmtAdmin.setString(1, login);
        stmtAdmin.setString(2, senha);
        ResultSet rsAdmin = stmtAdmin.executeQuery();
        if(rsAdmin.isBeforeFirst()){
            System.out.println(rsAdmin);
            return rsAdmin;
        }
        // Se não encontrou em nenhuma tabela
        return null;
    }

    /**
     * Este método realiza a ação de cadastrar um novo cliente.
     * @param cliente Cliente a ser cadastrado
     * @throws SQLException
     */
    public int cadastrar(Cliente cliente) throws SQLException {
        String insertCliente = "INSERT INTO Cliente (CPF,Endereco,fotoPerfil) VALUES (?,?,?)";
        String insertPessoa = "INSERT INTO Pessoa (CPF,Nome,Email,Senha,Data_Nascimento) VALUES (?,?,?,?,?)";
        String insertCarrinho = "INSERT INTO Carrinho (ID,Data_Criacao) VALUES (?,?)";

        try {
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            PreparedStatement stmtCliente = conn.prepareStatement(insertCliente);
            PreparedStatement stmtCarrinho = conn.prepareStatement(insertCarrinho);

            stmtPessoa.setString(1, cliente.getCPF());
            stmtPessoa.setString(2, cliente.getNome());
            stmtPessoa.setDate(5, cliente.getNascimento());
            stmtPessoa.setString(3, cliente.getLogin());
            stmtPessoa.setString(4, cliente.getSenha());

            stmtCliente.setString(1, cliente.getCPF());
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[4096];
                int bytesRead;
                FileInputStream fis = cliente.getImagemPerfil();
                while ((bytesRead = fis.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }

                byte[] imagemBytes = buffer.toByteArray();
                fis.close();
                stmtCliente.setBytes(3, imagemBytes);

            }
            catch (Exception e) {
            }
            stmtCarrinho.setString(1,cliente.getCPF());
            stmtCarrinho.setDate(2, new Date(1));

            stmtCarrinho.executeUpdate();
            stmtCarrinho.close();
            stmtCliente.executeUpdate();
            stmtCliente.close();
            stmtPessoa.executeUpdate();
            stmtPessoa.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Este método realiza a ação de cadastrar um novo vendedor.
     * @param vendedor Vendedor a ser cadastrado
     * @throws SQLException
     */
    public void cadastrarVendedor(Vendedor vendedor) throws SQLException {
        String insertCliente = "INSERT INTO Vendedor (CPF, Salario, Comissao, Avaliacao) VALUES (?,?,?,?)";
        String insertPessoa = "INSERT INTO Pessoa (CPF,Nome,Email,Senha,Data_Nascimento) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            PreparedStatement stmtCliente = conn.prepareStatement(insertCliente);

            stmtPessoa.setString(1, vendedor.getCPF());
            stmtPessoa.setString(2, vendedor.getNome());
            stmtPessoa.setDate(5, vendedor.getNascimento());

            stmtPessoa.setString(3, vendedor.getLogin());
            stmtPessoa.setString(4, vendedor.getSenha());
            stmtCliente.setString(1, vendedor.getCPF());

            stmtCliente.executeUpdate();
            stmtCliente.close();
            stmtPessoa.executeUpdate();
            stmtPessoa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retorna as informações de uma instancia Pessoa no Banco de Dados
     * @param login Administrador a ser cadastrado
     * @throws SQLException
     */
    public List<Object> GetPessoaByLogin(String login) throws SQLException {
        String sqlCliente = "SELECT *" +
                "FROM Cliente c INNER JOIN Pessoa p ON c.CPF = p.CPF WHERE p.Email = ?";

        String sqlFuncionario = "SELECT * " +
                "FROM Vendedor f INNER JOIN Pessoa p ON f.CPF = p.CPF WHERE p.Email = ?";

        String sqlAdmin = "SELECT * " +
                "FROM Administrador a INNER JOIN Pessoa p ON a.CPF = p.CPF WHERE p.Email = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> list = new ArrayList<>();

        try {
            // Verifica se é Cliente
            stmt = conn.prepareStatement(sqlCliente);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            FileInputStream fis = null;
            try {
                var getblob = rs.getBytes("fotoPerfil");
                File tempFile = File.createTempFile("tempfile", ".tmp");
                tempFile.deleteOnExit(); // Garante que o arquivo será excluído após a execução

                // Escrever os bytes no arquivo
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(getblob);
                }

                // Criar FileInputStream a partir do arquivo
                 fis = new FileInputStream(tempFile);
            }catch (Exception e){
            }
            if (rs.next()) {
                list.add(new Cliente(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getString("Senha"),
                        rs.getDate("Data_Nascimento"),
                        fis
                ));
                return list; // Retorna imediatamente se encontrar um cliente
            }
            rs.close();
            stmt.close();

            // Verifica se é Funcionário
            stmt = conn.prepareStatement(sqlFuncionario);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                list.add(new Vendedor(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getString("Senha"),
                        rs.getDate("Data_Nascimento"),
                        rs.getDouble("Salario"),
                        rs.getDouble("Comissao")
                ));
                return list; // Retorna imediatamente se encontrar um funcionário
            }
            rs.close();
            stmt.close();

            // Verifica se é Administrador
            stmt = conn.prepareStatement(sqlAdmin);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                list.add(new Administrador(
                        rs.getFloat("Salario"),
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getDate("Data_Nascimento"),
                        rs.getString("Email"),
                        rs.getString("Senha")
                ));
                return list;
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return list; // Retorna uma lista vazia caso o login não exista
    }


    /**
     * Este método realiza a ação de cadastrar um novo produto.
     * @param produto Produto a ser cadastrado
     * @param cliente Cliente que está cadastrando o produto
     * @param quantidade Quantidade de produtos a serem cadastrados
     * @throws SQLException
     */
    void inserirProdutoCarrinho(Produto produto,Cliente cliente,int quantidade) throws SQLException {
        if (quantidade > 0) {
            String sqlInsert =
                    "INSERT INTO" +
                            " item_carrinho (ID_Carrinho,ID_Produto,Quantidade)" +
                            " VALUES (?,?,?)" +
                            " ON CONFLICT " +
                            "(ID_Produto,ID_Carrinho)" +
                            " DO UPDATE SET" +
                            " Quantidade = excluded.Quantidade;";

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, cliente.getCPF());
            stmt.setInt(2, produto.getId());
            stmt.setInt(3, quantidade);

            stmt.executeUpdate();
            stmt.close();
        }
        else {
            String sqlRemove = "DELETE FROM item_carrinho WHERE ID_Carrinho = ? AND ID_Produto = ?";
            PreparedStatement removeStmt = conn.prepareStatement(sqlRemove);
            removeStmt.setString(1, cliente.getCPF());
            removeStmt.setInt(2, produto.getId());
            removeStmt.executeUpdate();
            removeStmt.close();
        }
    }
        HashMap<Produto,Integer> GetProdutosByCPF(String CPF) throws SQLException {
        String sqlSelect = "SELECT * FROM item_carrinho INNER JOIN Produto ON ID_Produto = ID WHERE ID_Carrinho = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();

        return Helper.converterProdutosCarrinho(rs);

    }

    /**
     * Este método realiza a ação de cadastrar um novo produto.
     * @param produto Produto a ser cadastrado
     * @param vendedor Vendedor que está cadastrando o produto
     * @throws SQLException
     */
    public void inserirProduto(Produto produto,Vendedor vendedor) throws SQLException {

        String sqlInsert =
                "INSERT INTO" +
                        " Produto (Nome,Preco,Estoque,Descricao,Imagem,ID_Vendedor,ID_Categoria)" +
                        " VALUES (?,?,?,?,?,?,?)" +
                        " ON CONFLICT " +
                        " DO UPDATE SET" +
                        " Estoque = Estoque + ?;";


        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setInt(3, produto.getEstoque());
        stmt.setString(4,produto.getDescricao());
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[4096];
            int bytesRead;
            FileInputStream fis = produto.getImagem();
            while ((bytesRead = fis.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }

            byte[] imagemBytes = buffer.toByteArray();
            fis.close();
            stmt.setBytes(5, imagemBytes);

        }
        catch (Exception e) {

        }
        stmt.setString(6,vendedor.getCPF());
        stmt.setString(7, produto.getCategoria());
        stmt.setInt(8, produto.getEstoque());
        System.out.println(stmt.toString());
        System.out.println("preco = "+produto.getPreco());
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Pesquisa um produto no banco de dados
     * @param query Produto a ser cadastrado
     * @throws SQLException
     * @return Lista de produtos encontrados
     */
    public List<Produto> pesquisaProdutos(String query) throws SQLException {
        String sqlSelect = "SELECT * FROM Produto WHERE produto.nome LIKE ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setString(1, "%" + query + "%"); // Adiciona os curingas à query
            try (ResultSet rs = stmt.executeQuery()) {
                produtos = Helper.converterProdutos(rs); // Converte o ResultSet em uma lista de produtos
            }
        } catch (SQLException e) {
            // Trate a exceção conforme necessário
            throw new SQLException("Erro ao buscar produtos: " + e.getMessage(), e);
        }

        return produtos;
    }


    /**
     * retorna a quantidade de produtos no carrinho
     * @param cpf Produto a ser cadastrado
     *
     * @throws SQLException
     * @return quantidade de produtos no carrinho
     */
    public int getQuantidadeCarrinho(String cpf) throws SQLException {
        String sqlSelect = "SELECT * FROM item_Carrinho WHERE ID_Carrinho = '"+cpf+"'";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        ResultSet rs = stmt.executeQuery();
        int qtd = 0;
        while (rs.next()) {
            qtd++;
        }
        System.out.println(cpf);
        System.out.println(qtd);
        System.out.println(stmt.toString());
        return qtd;
    }
    public List<Vendedor> getVendedoresByQuery(String query) throws SQLException {

        List<Vendedor> listaVendedores = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Pessoa p JOIN Vendedor v ON p.CPF = v.CPF WHERE p.CPF LIKE ? OR p.Nome LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                listaVendedores = Helper.converterVendedores(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaVendedores;
    }

    public void atualizarVendedor(Vendedor vendedor) {
        String sqlUpdatePessoa = "UPDATE Pessoa SET Nome = ? WHERE CPF = ?";
        String sqlUpdateVendedor = "UPDATE Vendedor SET Salario = ?, Comissao = ? WHERE CPF = ?";
        try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlUpdatePessoa);
             PreparedStatement stmtVendedor = conn.prepareStatement(sqlUpdateVendedor)) {
            stmtPessoa.setString(1, vendedor.getNome());
            stmtPessoa.setString(2, vendedor.getCPF());
            stmtPessoa.executeUpdate();

            stmtVendedor.setDouble(1, vendedor.getSalario());
            stmtVendedor.setDouble(2, vendedor.getComissao());
            stmtVendedor.setString(3, vendedor.getCPF());
            stmtVendedor.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto produto) {
        String sqlUpdate = "UPDATE Produto SET Nome = ?, Preco = ?, Estoque = ?, Descricao = ?, ID_Categoria = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setString(4, produto.getDescricao());
            //stmt.setString(5, produto.getImagem());
            stmt.setString(5, produto.getCategoria());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Vendedor> getVendedores()
    {
        String sqlSelect = "SELECT * FROM Vendedor inner join Pessoa on Vendedor.CPF = Pessoa.CPF";
        try {
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            ResultSet rs = stmt.executeQuery();
            return Helper.converterVendedores(rs);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Float getValorTotal(String cpf) throws SQLException {
        String sqlSelect = "SELECT quantidade,Preco FROM item_Carrinho inner join Produto WHERE ID_Carrinho = '"+cpf+"'";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        ResultSet rs = stmt.executeQuery();
        Float vt = 0f;
        while (rs.next()) {

            Integer quantidade = rs.getInt("quantidade");
            Float preco = rs.getFloat("Preco");
            vt+=preco*quantidade;
        }

        return vt;
    }
    public void limparCarrinhoByCpf(String cpf)
    {
        try {


            String sqlRemove = "DELETE FROM item_carrinho WHERE ID_Carrinho = ?";
            PreparedStatement removeStmt = conn.prepareStatement(sqlRemove);
            removeStmt.setString(1, cpf);
            removeStmt.executeUpdate();
            removeStmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Cliente getDefault()
    {
        String sqlSelectCliente = "SELECT * FROM Pessoa p JOIN Cliente c ON p.CPF = c.CPF WHERE p.CPF = '00000000000';";
        try {
            PreparedStatement stmt = conn.prepareStatement(sqlSelectCliente);
            ResultSet rs = stmt.executeQuery();

            FileInputStream fis = null;
            try {
                var getblob = rs.getBytes("fotoPerfil");
                File tempFile = File.createTempFile("tempfile", ".tmp");
                tempFile.deleteOnExit();

                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(getblob);
                }

                // Criar FileInputStream a partir do arquivo
                fis = new FileInputStream(tempFile);
            }catch (Exception e){
                e.printStackTrace();
            }
            if (rs.next()) {
                Cliente cliente = (new Cliente(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getString("Senha"),
                        rs.getDate("Data_Nascimento"),
                        fis
                ));
                return cliente;
            }
            rs.close();
            stmt.close();

            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}








