package br.ufpr.tcc.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.ufpr.model.Usuario;
import br.ufpr.service.handler.impl.UsuarioServiceHandlerImpl;


@SessionScoped
@ManagedBean(name="loginBean")
@Component
public class LoginBean {

	
	private static final long serialVersionUID = 1L;
	private Usuario user = new Usuario();
	private UsuarioServiceHandlerImpl usuarioService = new UsuarioServiceHandlerImpl();
	private boolean cadastro = false;
	private boolean usuario = false;
	private boolean professor = false;
	private boolean aluno = false;
	private boolean login = false;
	private boolean logout = false;
	
	
	public void init(){
		String nome = null;
		nome = (String)SecurityContextHolder.getContext().getAuthentication().getName();
		if(!nome.equals("anonymousUser")){
			user = usuarioService.findByLogin(nome);
			login = false;
			logout = true;
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
		}else{
			login = true;
			logout = false;
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



	public boolean isUsuario() {
		return usuario;
	}



	public void setUsuario(boolean usuario) {
		this.usuario = usuario;
	}



	public boolean isProfessor() {
		return professor;
	}



	public void setProfessor(boolean professor) {
		this.professor = professor;
	}



	public boolean isAluno() {
		return aluno;
	}



	public void setAluno(boolean aluno) {
		this.aluno = aluno;
	}



	public boolean isLogin() {
		return login;
	}



	public void setLogin(boolean login) {
		this.login = login;
	}



	public boolean isLogout() {
		return logout;
	}



	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	
}
