package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.dao.AutorDAO;
import br.ufsm.csi.aulaspringmvc.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorDAO autorDAO = new AutorDAO();

    @GetMapping
    public String getAutores(Model model) {
        model.addAttribute("autores", autorDAO.getAutores());
        return "autor/listar";
    }

    @GetMapping("/novo")
    public String showNovoAutorForm(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/editar";
    }

    @PostMapping("/salvar")
    public String salvarAutor(@ModelAttribute("autor") Autor autor, RedirectAttributes redirectAttributes) {
        String resultado;
        if (autor.getId_aut() == 0) {
            resultado = autorDAO.inserir(autor);
        } else {
            resultado = autorDAO.alterar(autor);
        }
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/autores";
    }

    @GetMapping("/editar/{id}")
    public String showEditarAutorForm(@PathVariable int id, Model model) {
        Autor autor = autorDAO.getAutorById(id);
        model.addAttribute("autor", autor);
        return "autor/editar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = autorDAO.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/autores";
    }
}