package br.ufpr.tcc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufpr.model.Acesso;
import br.ufpr.model.Aluno;
import br.ufpr.model.Usuario;
import br.ufpr.service.handler.impl.UsuarioServiceHandlerImpl;
@ViewScoped
@ManagedBean(name = "buscaUsuarioBean")
public class BuscaUsuario {

	private static final long serialVersionUID = 1L;
	
	private Integer tipoPesquisa;
	private Usuario usuario;
	private UsuarioServiceHandlerImpl usuarioService;
	private List<Usuario> lstUsuarios;
	private ResourceBundle rb;
	private boolean renderInfo;
	private String[] acessos;
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		tipoPesquisa = 1;
		usuario = new Usuario();
		usuarioService = new UsuarioServiceHandlerImpl();
		lstUsuarios = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
		//teste
		usuario.setNome("Rodrigo");
		usuario.setCpf("04943356966");
		acessos = new String[2];
		for(int i = 0; i > usuario.getAcessos().size(); i++){
			acessos[i] = usuario.getAcessos().get(i).getKey(); 
		}
	}
	
	
	public void salva(){
		usuarioService.update(usuario);
	}
	
	public String editaUsuario(){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("editUsuario", usuario);  
		return "cadastroUsuario.xhtml?faces-redirect=true";
	}
	
	
	

	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public boolean isRenderInfo() {
		return renderInfo;
	}


	public void setRenderInfo(boolean renderInfo) {
		this.renderInfo = renderInfo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Usuario> getLstUsuarios() {
		return lstUsuarios;
	}


	public void setLstUsuarios(List<Usuario> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}


	public String[] getAcessos() {
		return acessos;
	}


	public void setAcessos(String[] acessos) {
		this.acessos = acessos;
	}

	
	
}
