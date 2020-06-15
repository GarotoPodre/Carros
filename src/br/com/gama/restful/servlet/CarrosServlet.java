package br.com.gama.restful.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gama.restful.modelo.Carro;
import br.com.gama.restful.modelo.ListaCarros;
import br.com.gama.restful.service.CarroService;
import br.com.gama.restful.service.JAXBUtil;
import br.com.gama.restful.service.ServletUtil;

@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CarroService carroService=new CarroService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*Populando a lista com registros do bd*/
		List<Carro>carros=carroService.getCarros();
		
		/*Instanciando objeto que vai gerar meu XML*/
		ListaCarros lista=new ListaCarros();
		
		/*Adicionando minha lista no objeto*/
		lista.setCarros(carros);
		
		/*Gerando XML*/
		String xml=JAXBUtil.toXML(lista);
		
		/*Gerando JSON*/
		String json=JAXBUtil.toJSON(lista);
		
		/*Escrevendo o XML na response do servlet com application/xml*/
		//ServletUtil.writeXML(resp, xml);
		ServletUtil.writeJSON(resp, json);//(resp, xml);
		
	}
	
	
	

}
