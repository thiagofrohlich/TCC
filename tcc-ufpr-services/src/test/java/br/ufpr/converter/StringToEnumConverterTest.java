package br.ufpr.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.model.Genero;
import br.ufpr.support.SpringTestSupport;

public class StringToEnumConverterTest extends SpringTestSupport {

	@Autowired
	private Mapper mapper;
	
	@Test
	public void shouldConvertEnumToString() {
//		Given
		String m = "M";
		
//		When
		Genero genero = mapper.map(m, Genero.class);
		
//		Then
		assertNotNull(genero);
		assertEquals(m, genero.getKey());
		assertEquals(Genero.M.getValue(), genero.getValue());
		
	}
	
	@Test
	public void shouldConvertStringToEnum() {
//		Given
		Genero genero = Genero.F;
		
//		When
		String f = mapper.map(genero, String.class);
		
//		Then
		assertNotNull(f);
		assertEquals(Genero.F.getKey(), f);
	}
	
}
