package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.web.client.HttpServerErrorException;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.ufpr.model.Usuario;
import br.ufpr.service.handler.impl.UsuarioServiceHandlerImpl;
import br.ufpr.tcc.service.CEPHandler;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class CadastroUsuario implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private CEPHandler cepHandler;
	private UsuarioServiceHandlerImpl usuarioService;
	private boolean updateUsuario;
	private ResourceBundle rb;
	private CPFFormatter formatter;
	
	@PostConstruct
	public void init(){
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("editUsuario");
		if(usuario == null ){
			usuario = new Usuario();
			updateUsuario = false;
		}else{
			updateUsuario = true;
		}
		cepHandler = new CEPHandler();
		usuarioService = new UsuarioServiceHandlerImpl();
		rb = ResourceBundle.getBundle("msg");
		formatter = new CPFFormatter();
	}
	
	
	public void salvaUsuario(){
		if(validaUsuario()){
			usuario.setCpf(formatter.unformat(usuario.getCpf()));
			if(updateUsuario){
				usuarioService.update(usuario);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuário salvo com sucesso"));
			}else{
				usuarioService.create(usuario);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuário salvo com sucesso"));
			}
			limpaTela();
		}
	}
	
	private void limpaTela(){
		usuario = new Usuario();
	}
	
	public boolean validaUsuario(){
		boolean ret = true;
		if(usuario.getNome() == null || usuario.getNome().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronome")));
			ret = false;
		}
		if(usuario.getEmail() == null || usuario.getEmail().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroemail")));
			ret = false;
		}
		ret = verificaEndereco(usuario);
		if(usuario.getTelefone() == null || usuario.getTelefone().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errotelefone")));
			ret = false;
		}
		return ret;
	}
	
	public boolean verificaEndereco(Usuario endereco){
		boolean ret = true;
		if(endereco.getCep() == null || endereco.getCep().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errocep")));
			ret = false;
		}
		if(endereco.getCidade() == null || endereco.getCidade().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errocidade")));
			ret = false;
		}
		if(endereco.getEstado() == null || endereco.getEstado().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroestado")));
			ret = false;
		}
		
		if(endereco.getEndereco() == null || endereco.getEndereco().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroendereco")));
			ret = false;
		}
		if(endereco.getNumero() == null || endereco.getNumero().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronumero")));
			ret = false;
		}
		if(endereco.getPais() == null || endereco.getPais().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erropais")));
			ret = false;
		}
		return ret;
	}
	
	
	public void verificaCPF(){
		CPFValidator validator = new CPFValidator();
		try{
			validator.assertValid(usuario.getCpf());
			try{
				usuario = usuarioService.findByCpf(formatter.unformat(usuario.getCpf()));
			}catch(HttpServerErrorException e){
				
			}
		}catch(InvalidStateException e){
			FacesContext.getCurrentInstance().addMessage("messageAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "CPF INVÁILDO"));
		}
	}
	
	public void buscaCEP(){
		try{
			cepHandler.getEndereco(usuario);
		}catch (Exception e){
			FacesContext.getCurrentInstance().addMessage("messageAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public boolean isUpdateUsuario() {
		return updateUsuario;
	}


	public void setUpdateUsuario(boolean updateUsuario) {
		this.updateUsuario = updateUsuario;
	}
	
	
}
