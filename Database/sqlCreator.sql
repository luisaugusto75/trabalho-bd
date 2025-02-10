CREATE TABLE Pessoa (
    CPF CHAR(11) PRIMARY KEY,
    Nome VARCHAR(255),
    Data_Nascimento DATE,
    Telefone VARCHAR(20)
);

CREATE TABLE Cliente (
    CPF CHAR(11),
    Email VARCHAR(255),
    Senha VARCHAR(255),
    Endereco VARCHAR(255),
    fotoPerfil BLOB,
    PRIMARY KEY (CPF),
    FOREIGN KEY (CPF) REFERENCES Pessoa(CPF)
);

CREATE TABLE Administrador (
    CPF CHAR(11),
    Salario FLoat,
    PRIMARY KEY (CPF),
    FOREIGN KEY (CPF) REFERENCES Pessoa(CPF)
);

CREATE TABLE Vendedor (
    CPF CHAR(11),
    Salario FLoat,
    Comissao Float,
    Avaliacao Float,
    imagem BLOB,
    PRIMARY KEY (CPF),
    FOREIGN KEY (CPF) REFERENCES Pessoa(CPF)
);

CREATE TABLE Categoria (
    ID INT PRIMARY KEY,
    Nome VARCHAR(255),
    Descricao TEXT
);

CREATE TABLE Produto (
    ID INTEGER PRIMARY KEY AUTOINCREMENT ,
    Nome VARCHAR(255),
    Preco DECIMAL(10, 2),
    Estoque INT,
    Descricao TEXT,
    Imagem BLOB,
    ID_Vendedor INT,
    ID_Categoria INT,
    FOREIGN KEY (ID_Vendedor) REFERENCES Vendedor(CPF),
    FOREIGN KEY (ID_Categoria) REFERENCES Categoria(ID)
);

CREATE TABLE Pedido (
    ID INT PRIMARY KEY,
    Data DATE,
    Status VARCHAR(50),
    Total DECIMAL(10, 2),
    ID_Cliente INT,
    FOREIGN KEY (ID_Cliente) REFERENCES Cliente(CPF)
);

CREATE TABLE Item_Pedido (
    ID_Pedido INT,
    ID_Produto INT,
    Quantidade INT,
    Subtotal DECIMAL(10, 2),
    PRIMARY KEY (ID_Pedido, ID_Produto),
    FOREIGN KEY (ID_Pedido) REFERENCES Pedido(ID),
    FOREIGN KEY (ID_Produto) REFERENCES Produto(ID)
);

CREATE TABLE Carrinho (
    ID VARCHAR(45) PRIMARY KEY,
    Data_Criacao DATE,
    FOREIGN KEY (ID) REFERENCES Cliente(CPF)
);

CREATE TABLE Item_Carrinho (
    ID_Carrinho VARCHAR(11),
    ID_Produto INT,
    Quantidade INT,
    PRIMARY KEY (ID_Carrinho, ID_Produto),
    FOREIGN KEY (ID_Carrinho) REFERENCES Carrinho(ID),
    FOREIGN KEY (ID_Produto) REFERENCES Produto(ID)
);

CREATE TABLE Pagamento (
    ID INT PRIMARY KEY,
    Data DATE,
    Valor DECIMAL(10, 2),
    Metodo VARCHAR(50),
    Status VARCHAR(50),
    ID_Pedido INT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedido(ID)
);

SELECT * FROM item_Carrinho WHERE ID_Carrinho = '06654505166';

ALTER TABLE Cliente ADD COLUMN fotoPerfil BLOB;