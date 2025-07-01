package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.dao.AutorDAO;
import br.ufsm.csi.aulaspringmvc.dao.LivroDAO;
import br.ufsm.csi.aulaspringmvc.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroDAO livroDAO = new LivroDAO();
    private final AutorDAO autorDAO = new AutorDAO();

    @GetMapping
    public String getLivros(Model model) {
        model.addAttribute("livros", livroDAO.getLivros());
        return "livro/listar";
    }

    @GetMapping("/novo")
    public String showNovoLivroForm(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorDAO.getAutores());
        return "livro/editar";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute("livro") Livro livro, RedirectAttributes redirectAttributes) {
        String resultado;
        if (livro.getId_liv() == 0) {
            livro.setDisponivel_liv(true);
            resultado = livroDAO.inserir(livro);
        } else {
            resultado = livroDAO.alterar(livro);
        }
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String showEditarLivroForm(@PathVariable int id, Model model) {
        Livro livro = livroDAO.getLivroById(id);
        model.addAttribute("livro", livro);
        model.addAttribute("autores", autorDAO.getAutores());
        return "livro/editar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = livroDAO.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/livros";
    }
}