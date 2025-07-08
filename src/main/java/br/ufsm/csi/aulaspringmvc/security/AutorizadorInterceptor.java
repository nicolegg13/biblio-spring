package br.ufsm.csi.aulaspringmvc.security;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        //libera acesso a pagina de login e recursos estaticos (css etc)
        if (uri.equals("/") || uri.endsWith("/login") || uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/assets/")) {
            return true;
        }

        Object usuarioLogadoObj = request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogadoObj == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false; //bloqueia
        }

        Usuario usuario = (Usuario) usuarioLogadoObj;
        if(uri.startsWith("/autores") || uri.startsWith("/livros")){ //so admin pode acessar
            if ("ADMIN".equals(usuario.getTipo_us())) {
                return true; //se for admin pode acessar
            } else {
                request.getRequestDispatcher("/WEB-INF/acessoNegado.jsp").forward(request, response);
                return false; //se for usuario nao pode acessar
            }
        }

        //outras paginas acesso permitido para usuarios logados
        return true;
    }
}
