package br.com.gama.restful.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * A classe JAXBUtil consegue gerar XML, mas esse XML precisa ser enviado como
 * resposta da requisição HTTP, que é feita no Servlet. Nesse caso é recomendado
 * informar ao browser o tipo do conteúdo, que no caso do XML é padronizado como
 * text/xml ou application/xml, padronização conhecida como mime-type
 * Esse é o propósito dessa classe, escrever XML ou JSON na resposta de uma requisição
 * HTTP
 * Obs.: Deve se notar que o mime-type correto do XML ou do JSON é configurado
 * em cada método 
 * 
 * Lembrete :Os métodos static ou métodos da classe são funções que não dependem de 
 * nenhuma variável de instância, quando invocados executam uma função sem a dependência 
 * do conteúdo de um objeto ou a execução da instância de uma classe, conseguindo chamar 
 * direto qualquer método da classe e também manipulando alguns campos da classe.
 * 
 * @author azog
 */
public class ServletUtil {
	public static void writeXML(HttpServletResponse response, String xml) throws IOException{
		if(xml !=null) {
			PrintWriter writer=response.getWriter();
			response.setContentType("application/xml;charset=UTF-8");
			writer.write(xml);
			writer.close();
		}		
	}
	public static void writeJSON(HttpServletResponse response, String json) throws IOException{
		if(json !=null) {
			PrintWriter writer=response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}		
	}

	
}
