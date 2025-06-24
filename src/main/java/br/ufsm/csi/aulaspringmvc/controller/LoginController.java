package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final LoginService loginService = new LoginService();

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = loginService.autenticar(email, senha);
        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            session.setMaxInactiveInterval(3600); // 60 minutes
            if ("ADMIN".equals(usuario.getTipo_us())) {
                return "redirect:/home";
            } else {
                return "redirect:/meus-emprestimos";
            }
        } else {
            redirectAttributes.addFlashAttribute("erro", "Email ou senha incorretos");
            return "redirect:/";
        }
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null && "ADMIN".equals(usuario.getTipo_us())) {
            return "home";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}