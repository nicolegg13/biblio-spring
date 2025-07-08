package br.ufsm.csi.aulaspringmvc.service;
import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }

    public ArrayList<Usuario> getUsuariosAtivos() {
        return usuarioDAO.getUsuariosAtivos();
    }
}
