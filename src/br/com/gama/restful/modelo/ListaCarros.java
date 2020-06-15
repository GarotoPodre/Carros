package br.com.gama.restful.modelo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto wrapper que será usado para retornar a lista de carros, pois nativamente o JAXB não consegue.
 * XmlRootElement(name="carros") e XmlElement(name="carro") serão os nomes das tags que serão utilizadas
 * gerar o xml
 * @author azog
 *
 */

@XmlRootElement(name="carros")
public class ListaCarros implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Carro>carros;

	@XmlElement(name="carro")
	public List<Carro> getCarros(){
		return carros;
	}
	
	public void setCarros(List<Carro> carros) {
		this.carros=carros;
	}

	@Override
	public String toString() {
		return "ListaCarros [carros=" + carros + "]";
	}

}
