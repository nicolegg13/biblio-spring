package br.ufsm.csi.aulaspringmvc.service;

import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Usuario;

public class LoginService {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String email, String senha) {

        Usuario usuario = usuarioDAO.autenticar(email, senha);
        if (usuario == null) {
            return null;
        } else {
            return usuario;
        }

    }
}