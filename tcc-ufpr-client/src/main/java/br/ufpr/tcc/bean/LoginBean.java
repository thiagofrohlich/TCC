package br.ufpr.tcc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.ufpr.model.Usuario;


@SessionScoped
@ManagedBean(name="loginBean")
@Component
public class LoginBean {

	
	private static final long serialVersionUID = 1L;
	private Usuario user = new Usuario();
//	private UsuarioServiceHandler usuarioService = new UsuarioServiceHandler();
	private boolean cadastro = false;
	private boolean usuario = false;
	private boolean professor = false;
	private boolean aluno = false;
	
	public void recuperaUsuario(){
		String u = (String)SecurityContextHolder.getContext().getAuthentication().getName();
//		user = usuarioService.getByLogin(u);
		if(user.getAcessos().contains("ALUNO")){
			aluno = true;
		}
		if(user.getAcessos().contains("PROFESSOR")){
			professor = true;
		}
		if(user.getAcessos().contains("CADASTRO")){
			cadastro = true;
		}
		if(user.getAcessos().contains("USUARIO")){
			usuario = true;
		}
	}
	
	

	public Usuario getUser() {
		return user;
	}



	public void setUser(Usuario user) {
		this.user = user;
	}



	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

	
}
