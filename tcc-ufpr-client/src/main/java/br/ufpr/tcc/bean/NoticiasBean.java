package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;

import br.ufpr.model.Noticia;
import br.ufpr.service.handler.impl.NoticiaServiceHandlerImpl;
import br.ufpr.service.handler.impl.UsuarioServiceHandlerImpl;

@ViewScoped
@ManagedBean(name = "noticiasBean")
public class NoticiasBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle rb;
	private Noticia noticia;
	private NoticiaServiceHandlerImpl noticiaServiceHandlerImpl;
	private UsuarioServiceHandlerImpl usuarioServiceHandlerImpl;
	@PostConstruct
	public void init(){
		rb = ResourceBundle.getBundle("msg");
		noticia = new Noticia();
		noticia.setDataPublicacao(Calendar.getInstance().getTime());
		noticiaServiceHandlerImpl = new NoticiaServiceHandlerImpl();
		usuarioServiceHandlerImpl = new UsuarioServiceHandlerImpl();
	}
	
	
	public void salva(){
		if(verificaCampos()){
			noticia.setUsuario(usuarioServiceHandlerImpl.findByLogin((String)SecurityContextHolder.getContext().getAuthentication().getName()));
			noticiaServiceHandlerImpl.create(noticia);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Not�cia salva com sucesso"));
			limpaTela();
		}
	}
	
	public boolean verificaCampos(){
		boolean ret = true;
		if(noticia.getDataPublicacao() == null){
			ret = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor Selecione uma data"));
		}
		if(noticia.getTitulo() == null || noticia.getTitulo().equals("")){
			ret = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor preencha um titulo"));
		}
		if(noticia.getConteudo() == null || noticia.getConteudo().equals("")){
			ret = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Por favor preencha o conte�do da noticia"));
		}
		return ret;
	}
	
	private void limpaTela(){
		noticia = new Noticia();
		noticia.setDataPublicacao(Calendar.getInstance().getTime());
	}
	

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public NoticiaServiceHandlerImpl getNoticiaServiceHandlerImpl() {
		return noticiaServiceHandlerImpl;
	}

	public void setNoticiaServiceHandlerImpl(
			NoticiaServiceHandlerImpl noticiaServiceHandlerImpl) {
		this.noticiaServiceHandlerImpl = noticiaServiceHandlerImpl;
	}
	

	
}
