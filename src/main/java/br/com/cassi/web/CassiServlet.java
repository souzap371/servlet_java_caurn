package br.com.cassi.web;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/HelloWord")
public class CassiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Servlet Funcionando");
		response.getWriter().append("<html><h1>Servlet ok!</h1></html>");
		response.getWriter().append("Teste");
	}

}
