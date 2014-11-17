package br.ufpr.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.model.Acesso;
import br.ufpr.support.SpringTestSupport;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class StringToEnumListConverterComponentTest extends SpringTestSupport {

	@Autowired
	private Mapper mapper;
	
	@Test
	public void shouldTransformEnumListToString() {
//		Given
		List<Acesso> acessos  = Arrays.asList(Acesso.values());
		
//		When
		String acessosString = mapper.map(acessos, String.class);
		
//		Then
		assertNotNull(acessosString);
		for(Acesso a : acessos) {
			assertTrue(stringListContainsObject(acessosString, a));
		}
	}
	
	@Test
	public void shouldTransformStringToEnumList() {
//		Given
		String acessosString = Acesso.ALUNO.getKey();
		
//		When
		List acessos = mapper.map(acessosString, List.class);
		
//		Then
		assertNotNull(acessos);
		for(String s : acessosString.split(",")) {
			assertTrue(listContainsString(acessos, s));
		}
	}
	
	private boolean stringListContainsObject(String stringList, Acesso a) {
		boolean contains = false;
		for(String s : stringList.split(",")) {
			if(s.equals(a.getKey())) {
				contains = true;
			}
		}
		return contains;
	}
	
	private boolean listContainsString(List<Acesso> acessos, String stringObject) {
		boolean contains = false;
		for(Acesso a : acessos) {
			if(a.getKey().equals(stringObject)) {
				contains = true;
			}
		}
		return contains;
	}
	
}
