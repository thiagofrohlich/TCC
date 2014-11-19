package br.ufpr.wrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;

import br.ufpr.model.Noticia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso(Noticia.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NoticiaWrapper extends Wrapper<Noticia> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoticiaWrapper(Page<? extends Object> page) {
		super(page);
	}

	public NoticiaWrapper(){
		super();
	}
}
