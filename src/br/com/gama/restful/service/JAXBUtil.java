package br.com.gama.restful.service;

import java.io.IOException;
//import java.io.StringWriter;

import com.google.gson.Gson;

//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;

import com.thoughtworks.xstream.XStream;

import br.com.gama.restful.modelo.Carro;
import br.com.gama.restful.modelo.ListaCarros;

/**
 * PAra usar JAXB para gerar xml, é necessário criar essa classe 
 * @author azog
 *
 */
public class JAXBUtil {
	private static JAXBUtil instance;
	//private static JAXBContext context;
	
	public static JAXBUtil getInstance() {
		return instance;
	}
	
	//static {
	//	try {
			/*Informa ao JAXB que é para gerar XML destas classes*/
	//		context=JAXBContext.newInstance(ListaCarros.class, Carro.class);
	//	}catch(JAXBException e) {
	//		throw new RuntimeException(e);
	//	}
	//}
	/*Converte o objeto para XML, processo conhecido no JAXB por Marshaller, por isso o 
	 * método cria um objeto Marshaller 
	 * */
	public static String toXML(Object object) throws IOException{
//		try {
//			StringWriter writer = new StringWriter();
//			Marshaller m=context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			m.marshal(object, writer);
//			String xml=writer.toString();
		/*Tive que apelar pois o JAXB foi removido das versões mais novas do Java
		 * Essa biblioteca é bem mais simples.*/
		XStream xStream=new XStream();
		String xml=xStream.toXML(object);
		return xml.toString();
//		
		//}catch(JAXBException e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	
	public static String toJSON(Object object) throws IOException{
		return new Gson().toJson(object).toString();
	}
}
