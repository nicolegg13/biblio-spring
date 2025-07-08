package br.ufsm.csi.aulaspringmvc.controller;
import br.ufsm.csi.aulaspringmvc.model.Emprestimo;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.EmprestimoService;
import br.ufsm.csi.aulaspringmvc.service.LivroService;
import br.ufsm.csi.aulaspringmvc.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * lida com questoes http e coordena o fluxo
 * sem acesso direto ao DAO
 * */

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService; //guarda objeto do serviço
    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService, UsuarioService usuarioService, LivroService livroService) { //recebe serviço como parametro
        this.emprestimoService = emprestimoService;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    @GetMapping
    public String getEmprestimos(Model model, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/"; // se não estiver logado, volta para a página inicial
        }

        if ("ADMIN".equals(usuarioLogado.getTipo_us())) {
            model.addAttribute("emprestimos", emprestimoService.getEmprestimos());
        } else {
            // Se for usuário comum, busca apenas os empréstimos dele
            model.addAttribute("emprestimos", emprestimoService.getEmprestimosPorUsuario(usuarioLogado.getId_us()));
        }

        return "emprestimo/listar";
    }

    @GetMapping("/novo")
    public String showNovoEmprestimoForm(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroService.getLivrosDisponiveis());
        model.addAttribute("usuarios", usuarioService.getUsuariosAtivos());
        return "emprestimo/editar";
    }

    @PostMapping("/salvar")
    public String salvarEmprestimo(@ModelAttribute("emprestimo") Emprestimo emprestimo, RedirectAttributes ra) {
        //recebe dados do formulario (ModelAttribute)
        //chama o serviço no objeto emprestimoService de EmprestimoService
        //redireciona o usuário
        String resultado = emprestimoService.inserir(emprestimo);
        ra.addFlashAttribute("mensagem", resultado);
        return "redirect:/emprestimos";
    }

    @PostMapping("/devolver/{id}")
    public String devolverEmprestimo(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = emprestimoService.devolver(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/emprestimos";
    }
}