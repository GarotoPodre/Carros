package br.com.gama.restful.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gama.restful.modelo.Carro;
import br.com.gama.restful.service.CarroService;

@WebServlet("/carros/*")
public class CarroServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CarroService carroService=new CarroService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Carro> carros=carroService.getCarros();
		String carrosString=carros.toString();
		
		
		resp.getWriter().write(carrosString);
	}
	
	
	

}
