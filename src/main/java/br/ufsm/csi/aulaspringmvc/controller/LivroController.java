package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.dao.AutorDAO;
import br.ufsm.csi.aulaspringmvc.dao.LivroDAO;
import br.ufsm.csi.aulaspringmvc.model.Livro;
import br.ufsm.csi.aulaspringmvc.service.AutorService;
import br.ufsm.csi.aulaspringmvc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final AutorService autorService;
    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    @GetMapping
    public String getLivros(Model model) {
        model.addAttribute("livros", livroService.getLivros());
        return "livro/listar";
    }

    @GetMapping("/novo")
    public String showNovoLivroForm(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorService.getAutores());
        return "livro/editar";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute("livro") Livro livro, RedirectAttributes redirectAttributes) {
        String resultado = livroService.salvar(livro);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String showEditarLivroForm(@PathVariable int id, Model model) {
        Livro livro = livroService.getLivroById(id);
        model.addAttribute("livro", livro);
        model.addAttribute("autores", autorService.getAutores());
        return "livro/editar";
    }

    @PostMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = livroService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/livros";
    }
}