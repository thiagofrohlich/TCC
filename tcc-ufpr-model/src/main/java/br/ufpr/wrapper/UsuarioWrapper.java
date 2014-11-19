
package br.ufpr.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;

import br.ufpr.model.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso(Usuario.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UsuarioWrapper extends Wrapper<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioWrapper(Page<? extends Object> page) {
		super(page);
	}
	
	public UsuarioWrapper(){
		super();
	}

	public UsuarioWrapper(List<Usuario> returnList) {
		super(returnList);
	}

}
