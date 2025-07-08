package br.ufsm.csi.aulaspringmvc.service;

import br.ufsm.csi.aulaspringmvc.dao.EmprestimoDAO;
import br.ufsm.csi.aulaspringmvc.dao.LivroDAO;
import br.ufsm.csi.aulaspringmvc.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LivroService {
    private final LivroDAO livroDAO;
    private final EmprestimoDAO emprestimoDAO;

    //construtor
    public LivroService(LivroDAO livroDAO, EmprestimoDAO emprestimoDAO) {
        this.livroDAO = livroDAO;
        this.emprestimoDAO = emprestimoDAO;
    }

    /*public String salvar(Livro livro) {
        if (livro.getId_liv() == 0) {
            //regra de negocio
            livro.setDisponivel_liv(true);
            return livroDAO.inserir(livro);
        } else {
            return livroDAO.alterar(livro);
        }
    }*/
    public String salvar(Livro livro){
        if (livro.getId_liv() == 0) {
            //insere novo
            //regras de negocio
            livro.setDisponivel_liv(true);
            livro.setAtivo_liv(true);
            return livroDAO.inserir(livro);
        } else {
            //alterar
            //busca no banco
            Livro livroBanco = livroDAO.getLivroById(livro.getId_liv());
            //atualiza os dados
            livroBanco.setTitulo_liv(livro.getTitulo_liv());
            livroBanco.setIsbn_liv(livro.getIsbn_liv());
            livroBanco.setAno_publicacao_liv(livro.getAno_publicacao_liv());
            livroBanco.setDisponivel_liv(livro.isDisponivel_liv());
            livroBanco.setId_autor_liv(livro.getId_autor_liv());

            return livroDAO.alterar(livroBanco);
        }
    }

    public String excluir(int id) {
        //verfifica se o livro tem emprestimos ativos
        if(emprestimoDAO.temEmprestimosAtivos(id)) {
            return "O livro não pode ser excluído pois tem empréstimos ativos";
        }
        return livroDAO.inativar(id);
    }

    public Livro getLivroById(int id) {
        return livroDAO.getLivroById(id);
    }

    public ArrayList<Livro> getLivros() {
        return livroDAO.getLivros();
    }

    public ArrayList<Livro> getLivrosDisponiveis() {
        return livroDAO.getLivrosDisponiveis();
    }
}
