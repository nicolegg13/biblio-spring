package br.ufsm.csi.aulaspringmvc.service;

import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UsuarioDAO usuarioDAO;
    public LoginService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String email, String senha) {
        //Usuario usuario = usuarioDAO.autenticar(email, senha);
        Usuario usuario = usuarioDAO.autenticar(email, senha);
        return usuario; //ou null
    }
}