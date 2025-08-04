-- Garante a criação da extensão para senhas (opcional, mas boa prática)
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Tabela de Usuários
CREATE TABLE usuario (
    id_us SERIAL PRIMARY KEY,
    nome_us VARCHAR(255) NOT NULL,
    email_us VARCHAR(255) UNIQUE NOT NULL,
    senha_us VARCHAR(255) NOT NULL,
    tipo_us VARCHAR(50) NOT NULL CHECK (tipo_us IN ('ADMIN', 'USUARIO')),
    ativo_us BOOLEAN DEFAULT true
);

-- Tabela de Autores
CREATE TABLE autor (
    id_aut SERIAL PRIMARY KEY,
    nome_aut VARCHAR(255) NOT NULL,
    nacionalidade_aut VARCHAR(100),
    data_nascimento_aut DATE
);

-- Tabela de Livros
CREATE TABLE livro (
    id_liv SERIAL PRIMARY KEY,
    titulo_liv VARCHAR(255) NOT NULL,
    isbn_liv VARCHAR(50),
    ano_publicacao_liv INT,
    id_autor_liv INT,
    disponivel_liv BOOLEAN DEFAULT true,
    CONSTRAINT fk_autor FOREIGN KEY (id_autor_liv) REFERENCES autor(id_aut) ON DELETE SET NULL
);

-- Tabela de Empréstimos
CREATE TABLE emprestimo (
    id_emp SERIAL PRIMARY KEY,
    id_livro_emp INT NOT NULL,
    id_usuario_emp INT NOT NULL,
    data_emprestimo_emp DATE NOT NULL,
    data_devolucao_prevista_emp DATE,
    data_devolucao_efetiva_emp DATE,
    status_emp VARCHAR(50) CHECK (status_emp IN ('ATIVO', 'CONCLUIDO', 'ATRASADO')),
    CONSTRAINT fk_livro FOREIGN KEY (id_livro_emp) REFERENCES livro(id_liv),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario_emp) REFERENCES usuario(id_us)
);

INSERT INTO usuario (nome_us, email_us, senha_us, tipo_us, ativo_us)
VALUES ('Administrador', 'admin@biblioteca.com', 'admin123', 'ADMIN', true);

INSERT INTO usuario (nome_us, email_us, senha_us, tipo_us, ativo_us)
VALUES ('Usuario', 'usuario@biblioteca.com', 'user123', 'USUARIO', true);