package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.dao.EmprestimoDAO;
import br.ufsm.csi.aulaspringmvc.dao.LivroDAO;
import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Emprestimo;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import jakarta.servlet.http.HttpSession;
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
    public String getEmprestimos(Model model, HttpSession session) { // Adicionado HttpSession
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/"; // Se não estiver logado, volta para a página inicial
        }

        if ("ADMIN".equals(usuarioLogado.getTipo_us())) {
            model.addAttribute("emprestimos", emprestimoDAO.getEmprestimos());
        } else {
            // Se for usuário comum, busca apenas os empréstimos dele
            model.addAttribute("emprestimos", emprestimoDAO.getEmprestimosPorUsuario(usuarioLogado.getId_us()));
        }

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

        emprestimo.setData_emprestimo_emp(new java.sql.Date(System.currentTimeMillis()));
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, 14); // Adiciona 14 dias para a devolução
        emprestimo.setData_devolucao_prevista_emp(new java.sql.Date(cal.getTimeInMillis()));
        emprestimo.setStatus_emp("ATIVO");

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