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
        return autorDAO.excluir(id);
    }

    public Autor getAutorById(int id) {
        return autorDAO.getAutorById(id);
    }

    public ArrayList<Autor> getAutores() {
        return autorDAO.getAutores();
    }
}
