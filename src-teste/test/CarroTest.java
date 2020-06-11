package test;
/**
 * IMPORTATNTE
 * No junit 3, todos os métodos que se iniciam com 'test' são testados
 * No junit 4, é necessário anotar o método com '@Test'
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.gama.restful.modelo.Carro;
import br.com.gama.restful.service.CarroService;

class CarroTest {

	private CarroService carroService=new CarroService();
	
	@Test
	public void testListaCarros() {
		List<Carro>carros=carroService.getCarros();
		assertNotNull(carros);
		
		assertTrue(carros.size()>0);
				
		Carro tucker=carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());
		
		Carro ferrari=carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		
		Carro bugatti=carroService.findByName("Bugatti Veryon").get(0);
		assertEquals("Bugatti Veryon", bugatti.getNome());

		//fail("Not yet implemented");
	}

}
