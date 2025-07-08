package br.ufsm.csi.aulaspringmvc.dao;
import br.ufsm.csi.aulaspringmvc.model.Livro;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;

@Repository //padrao para DAOs
public class LivroDAO {

    public String inserir(Livro livro) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres()) {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO livro (titulo_liv, isbn_liv, ano_publicacao_liv, id_autor_liv, disponivel_liv, ativo_liv) VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setString(1, livro.getTitulo_liv());
            stmt.setString(2, livro.getIsbn_liv());
            stmt.setInt(3, livro.getAno_publicacao_liv());
            stmt.setInt(4, livro.getId_autor_liv());
            stmt.setBoolean(5, livro.isDisponivel_liv());
            stmt.setBoolean(6, livro.isAtivo_liv());
            stmt.executeUpdate();
            conn.commit();
            return "Livro inserido com sucesso";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao inserir livro";
        }
    }

    public ArrayList<Livro> getLivros() {
        ArrayList<Livro> livros = new ArrayList<>();
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres()) {
            String sql = "SELECT l.*, a.nome_aut as nome_autor " +
                    "FROM livro l JOIN autor a ON l.id_autor_liv = a.id_aut WHERE l.ativo_liv = true";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Livro l = new Livro();
                l.setId_liv(rs.getInt("id_liv"));
                l.setTitulo_liv(rs.getString("titulo_liv"));
                l.setIsbn_liv(rs.getString("isbn_liv"));
                l.setAno_publicacao_liv(rs.getInt("ano_publicacao_liv"));
                l.setId_autor_liv(rs.getInt("id_autor_liv"));
                l.setDisponivel_liv(rs.getBoolean("disponivel_liv"));
                l.setAtivo_liv(rs.getBoolean("ativo_liv"));

                // Adiciona o nome do autor
                l.setNome_autor(rs.getString("nome_autor"));
                livros.add(l);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public ArrayList<Livro> getLivrosDisponiveis() {
        ArrayList<Livro> livros = new ArrayList<>();
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT l.*, a.nome_aut FROM livro l JOIN autor a ON l.id_autor_liv = a.id_aut WHERE l.disponivel_liv = true AND l.ativo_liv = true"
             )) {
            while (rs.next()) {
                Livro l = new Livro();
                l.setId_liv(rs.getInt("id_liv"));
                l.setTitulo_liv(rs.getString("titulo_liv"));
                l.setIsbn_liv(rs.getString("isbn_liv"));
                l.setAno_publicacao_liv(rs.getInt("ano_publicacao_liv"));
                l.setId_autor_liv(rs.getInt("id_autor_liv"));
                l.setDisponivel_liv(rs.getBoolean("disponivel_liv"));
                l.setAtivo_liv(rs.getBoolean("ativo_liv"));
                l.setNome_autor(rs.getString("nome_aut"));
                livros.add(l);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return livros;
    }

    public Livro getLivroById(int id) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT l.*, a.nome_aut FROM livro l JOIN autor a ON l.id_autor_liv = a.id_aut WHERE l.id_liv = ?"
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Livro l = new Livro();
                l.setId_liv(rs.getInt("id_liv"));
                l.setTitulo_liv(rs.getString("titulo_liv"));
                l.setIsbn_liv(rs.getString("isbn_liv"));
                l.setAno_publicacao_liv(rs.getInt("ano_publicacao_liv"));
                l.setId_autor_liv(rs.getInt("id_autor_liv"));
                l.setDisponivel_liv(rs.getBoolean("disponivel_liv"));
                l.setAtivo_liv(rs.getBoolean("ativo_liv"));
                l.setNome_autor(rs.getString("nome_aut"));
                return l;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String alterar(Livro livro) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE livro SET titulo_liv = ?, isbn_liv = ?, ano_publicacao_liv = ?, id_autor_liv = ?, disponivel_liv = ?, ativo_liv = ? WHERE id_liv = ?"
            );
            stmt.setString(1, livro.getTitulo_liv());
            stmt.setString(2, livro.getIsbn_liv());
            stmt.setInt(3, livro.getAno_publicacao_liv());
            stmt.setInt(4, livro.getId_autor_liv());
            stmt.setBoolean(5, livro.isDisponivel_liv());
            stmt.setBoolean(6, livro.isAtivo_liv());
            stmt.setInt(7, livro.getId_liv());
            int updateCount = stmt.executeUpdate();
            if (updateCount <= 0) {
                return "Nenhum livro atualizado";
            }
            return "Livro atualizado com sucesso";
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return "Erro ao atualizar livro";
        }
    }

    //"excluir" mas mantem no historico de registros
    public String inativar(int id) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE livro SET ativo_liv = false WHERE id_liv = ?");

            stmt.setInt(1, id);

            int updateCount = stmt.executeUpdate();
            if (updateCount <= 0) {
                return "Nenhum livro encontrado";
            }
            return "Livro removido do acervo com sucesso";
        } catch (SQLException ex) {
            if ("23503".equals(ex.getSQLState())) {
                return "Erro ao excluir: O livro está associado a um ou mais empréstimos.";
            }
            ex.printStackTrace();
            return "Erro ao remover livro: " + ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao remover livro: " + e.getMessage();
        }
    }
}