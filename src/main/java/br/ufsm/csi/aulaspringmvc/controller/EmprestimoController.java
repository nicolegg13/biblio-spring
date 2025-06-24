package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.dao.EmprestimoDAO;
import br.ufsm.csi.aulaspringmvc.dao.LivroDAO;
import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Emprestimo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private final LivroDAO livroDAO = new LivroDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GetMapping
    public String getEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoDAO.getEmprestimos());
        return "emprestimo/listar";
    }

    @GetMapping("/novo")
    public String showNovoEmprestimoForm(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroDAO.getLivrosDisponiveis());
        model.addAttribute("usuarios", usuarioDAO.getUsuariosAtivos());
        return "emprestimo/editar";
    }

    @PostMapping("/salvar")
    public String salvarEmprestimo(@ModelAttribute("emprestimo") Emprestimo emprestimo, RedirectAttributes redirectAttributes) {
        String resultado = emprestimoDAO.inserir(emprestimo);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/emprestimos";
    }

    @GetMapping("/devolver/{id}")
    public String devolverEmprestimo(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = emprestimoDAO.devolver(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/emprestimos";
    }
}