package br.ufsm.csi.aulaspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        // Retorna o nome da view "login".
        // O Spring vai procurar por um arquivo chamado "login.jsp".
        System.out.println("index");
        return "login";
    }
}