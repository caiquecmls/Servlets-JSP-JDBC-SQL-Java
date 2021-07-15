package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

import connection.SingleConnectionBanco;

@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {

	}

	// Encerra os processo quando o servidor é parado
	// Mataria os processos de conexão com o banco
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Intercepta todas as requisições e a as resposta no sistema
	// tudo que fizer no sistema passa por ele
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath(); // URL que está sendo acessada

			// Validar se está logado, senão direciona para tela de login

			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return; // para a execução e redireciona para o login
			} else {
				chain.doFilter(request, response);
			}
			connection.commit(); // deu tudo certo, então comita no banco de dados
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// inicia os processo ou recursos quando o servidor sobe o projeto
	// iniciar conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
