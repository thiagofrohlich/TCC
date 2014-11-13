package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.ufpr.model.Aluno;
import br.ufpr.model.Professor;
import br.ufpr.service.handler.impl.ProfessorServiceHandlerImpl;
import br.ufpr.tcc.service.CEPHandler;

@ViewScoped
@ManagedBean(name = "professorBean")
public class CadastroProfessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Professor professor;
	private CEPHandler cepHandler;
	private ProfessorServiceHandlerImpl professorService;
	private boolean updateProfessor;
	private ResourceBundle rb;
	
	@PostConstruct
	public void init(){
		professor = (Professor) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("editProfessor");
		if(professor == null){
			professor = new Professor();
			updateProfessor = false;
		}else{
			updateProfessor = true;
		}
		cepHandler = new CEPHandler();
		professorService = new ProfessorServiceHandlerImpl();
		rb = ResourceBundle.getBundle("msg");
	}
	
	
	public void salvaProfessor(){
		if(validaProfessor()){
			if(updateProfessor){
				professorService.update(professor);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Professor salvo com sucesso"));
			}else{
				professorService.create(professor);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Professor salvo com sucesso"));
			}
			limpaTela();
		}
	}
	
	private void limpaTela(){
		professor = new Professor();
	}
	
	public boolean validaProfessor(){
		boolean ret = true;
		if(professor.getNome() == null || professor.getNome().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronome")));
			ret = false;
		}
		if(professor.getEmail() == null || professor.getEmail().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroemail")));
			ret = false;
		}
		ret = verificaEndereco(professor);
		if(professor.getTelefone() == null || professor.getTelefone().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errotelefone")));
			ret = false;
		}
		return ret;
	}
	
	public boolean verificaEndereco(Professor endereco){
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
			validator.assertValid(professor.getCpf());
			
		}catch(InvalidStateException e){
			FacesContext.getCurrentInstance().addMessage("messageAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "CPF INVÁILDO"));
		}
	}
	
	public void buscaCEP(){
		try{
			cepHandler.getEndereco(professor);
		}catch (Exception e){
			FacesContext.getCurrentInstance().addMessage("messageAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
}
