package br.ufsm.csi.aulaspringmvc.service;
import br.ufsm.csi.aulaspringmvc.dao.EmprestimoDAO;
import br.ufsm.csi.aulaspringmvc.dao.LivroDAO;
import br.ufsm.csi.aulaspringmvc.model.Emprestimo;
import br.ufsm.csi.aulaspringmvc.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * contem as regras de negocio, implementa e faz o registro do emprestimo em si
 * verificar se o livro está disponivel
 * define data atual como inicio do emprestimo
 * calcula data final (+14d)
 * define status como ativo
 */

@Service
public class EmprestimoService {
    private final EmprestimoDAO emprestimoDAO;
    private final LivroDAO livroDAO;

    //construtor
    public EmprestimoService(EmprestimoDAO emprestimoDAO, LivroDAO livroDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.livroDAO = livroDAO;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimoDAO.getEmprestimos();
    }

    public ArrayList<Emprestimo> getEmprestimosPorUsuario(int idUsuario) {
        return emprestimoDAO.getEmprestimosPorUsuario(idUsuario);
    }

    public String devolver(int idEmprestimo) {
        return emprestimoDAO.devolver(idEmprestimo);
    }

    public String inserir(Emprestimo emprestimo) {
        Livro livro = livroDAO.getLivroById(emprestimo.getId_livro_emp());
        if(livro == null || !livro.isDisponivel_liv()) {
            return "livro não disponível ou não encontrado";
        }
        emprestimo.setData_emprestimo_emp(new java.sql.Date(System.currentTimeMillis()));
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, 14); // add 14 dias para a devolução
        emprestimo.setData_devolucao_prevista_emp(new java.sql.Date(cal.getTimeInMillis()));
        emprestimo.setStatus_emp("ATIVO");
        //chama DAO
        return emprestimoDAO.inserir(emprestimo);
    }
}
