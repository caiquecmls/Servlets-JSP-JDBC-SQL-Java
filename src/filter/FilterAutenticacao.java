package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todas as requisi��es que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

	public FilterAutenticacao() {

	}

	// Encerra os processo quando o servidor � parado
	// Mataria os processos de conex�o com o banco
	public void destroy() {
	}

	// Intercepta todas as requisi��es e a as resposta no sistema
	// tudo que fizer no sistema passa por ele
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath(); // URL que est� sendo acessada

		// Validar se est� logado, sen�o direciona para tela de login

		if (usuarioLogado == null && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);
			return; // para a execu��o e redireciona para o login
		} else {
			chain.doFilter(request, response);
		}
	}

	// inicia os processo ou recursos quando o servidor sobe o projeto
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
