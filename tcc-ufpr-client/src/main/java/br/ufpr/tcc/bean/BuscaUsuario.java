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
	private String nomeUsuario;
	private Usuario usuarioSelecionado;
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		tipoPesquisa = 1;
		usuario = new Usuario();
		usuarioService = new UsuarioServiceHandlerImpl();
		lstUsuarios = new ArrayList<>();
		usuarioSelecionado = new Usuario();
		rb = ResourceBundle.getBundle("msg");
		//teste
		
		acessos = new String[2];
		
	}
	
	
	public void salva(){
		usuarioService.update(usuario);
	}
	
	public String editaUsuario(){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("editUsuario", usuario);  
		return "cadastroUsuario.xhtml?faces-redirect=true";
	}
	
	public void buscaUsuarioPorNome(){
		lstUsuarios = usuarioService.findByNome(nomeUsuario);
	}
	
	
	public void selecionaUsuario(){
		usuario = usuarioSelecionado;
		usuarioSelecionado = new Usuario();
		for(int i = 0; i > usuario.getAcessos().size(); i++){
			acessos[i] = usuario.getAcessos().get(i).getKey(); 
		}
		renderInfo = true;
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


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}


	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	
	
}
