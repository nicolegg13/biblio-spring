package br.ufsm.csi.aulaspringmvc.service;

import br.ufsm.csi.aulaspringmvc.dao.AutorDAO;
import br.ufsm.csi.aulaspringmvc.model.Autor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutorService {
    private final AutorDAO autorDAO;

    //construtor
    public AutorService(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
    }

    public String salvar(Autor autor) {
        if (autor.getId_aut() == 0) {
            return autorDAO.inserir(autor);
        } else {
            return autorDAO.alterar(autor);
        }
    }

    public String excluir(int id) {
        String resultado = autorDAO.excluir(id);
        if (resultado.contains("viola restrição de chave estrangeira")) {
            return "Erro: O autor não pode ser excluído pois possui livros cadastrados";
        }
        return resultado;
    }

    public Autor getAutorById(int id) {
        return autorDAO.getAutorById(id);
    }

    public ArrayList<Autor> getAutores() {
        return autorDAO.getAutores();
    }
}
