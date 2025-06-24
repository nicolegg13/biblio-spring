package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.dao.EmprestimoDAO;
import br.ufsm.csi.aulaspringmvc.model.Emprestimo;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/meus-emprestimos")
public class MeusEmprestimosController {

    private final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    @GetMapping
    public String getMeusEmprestimos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null) {
            model.addAttribute("emprestimos", emprestimoDAO.getEmprestimosPorUsuario(usuario.getId_us()));
            return "emprestimo/meus-emprestimos";
        }
        return "redirect:/";
    }

    @GetMapping("/devolver/{id}")
    public String devolverMeuEmprestimo(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = emprestimoDAO.devolver(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/meus-emprestimos";
    }
}