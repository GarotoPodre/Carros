package test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * IMPORTATNTE
 * No junit 3, todos os m√©todos que se iniciam com 'test' s√£o testados
 * No junit 4, √© necess√°rio anotar o m√©todo com '@Test'
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
		
		//assertTrue(carros.size()>0);
		assertTrue("Tamanho de carros - "+carros.size(), carros.size()>0);
				
		Carro tucker=carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());
		
		Carro ferrari=carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		
		Carro bugatti=carroService.findByName("Bugatti Veryon").get(0);
		assertEquals("Bugatti Veryon", bugatti.getNome());

		//fail("Not yet implemented");
		
	}
	
	@Test
	public void testSalvarDeletarCarro() {
		Carro c=new Carro();
		
		c.setNome("Kombi");
		c.setDescricao("Cl·ssico");
		c.setUrlFoto("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Volkswagen_221031_dutch_licence_registration_13-YA-60_pic3.JPG/300px-Volkswagen_221031_dutch_licence_registration_13-YA-60_pic3.JPG");
		c.setUrlVideo("https://youtu.be/aJwRwwW96C8");
		c.setLatitude("lat");
		c.setLongitude("long");
		c.setTipo("utilit·rio");
		carroService.sabe(c);
		
		Long id=c.getId();
		//assertNotNull(id);
		assertNotNull("id = "+id, id);
		
		c=carroService.getCarroById(id);
		
		assertEquals("Kombi", c.getNome());
		assertEquals("Cl·ssico", c.getDescricao());
		assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Volkswagen_221031_dutch_licence_registration_13-YA-60_pic3.JPG/300px-Volkswagen_221031_dutch_licence_registration_13-YA-60_pic3.JPG", c.getUrlFoto());
		assertEquals("https://youtu.be/aJwRwwW96C8", c.getUrlVideo());
		assertEquals("lat", c.getLatitude());
		assertEquals("long", c.getLongitude());
		assertEquals("utilit·rio", c.getTipo());
		
		/*update*/
		c.setNome("Kombosa");
		carroService.sabe(c);
		
		c=carroService.getCarroById(id);
		assertEquals("Kombosa", c.getNome());
		
		
	}

}
