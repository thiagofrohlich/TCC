package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.Calendar;
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
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.model.Aluno;
import br.ufpr.service.handler.impl.AlunoServiceHandlerImpl;
import br.ufpr.tcc.service.CEPHandler;

@ViewScoped
@ManagedBean(name = "alunoBean")
public class CadastroAluno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private CEPHandler cepHandler;
	private AlunoServiceHandlerImpl alunoService;
	private ResourceBundle rb;
	private boolean updateAluno;
	private CPFFormatter formatter;
	
	@PostConstruct
	public void init(){
		aluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("editAluno");
		if(aluno == null){
			aluno = new Aluno();
			aluno.setAtivo(true);
			updateAluno = false;
		}else{
			updateAluno = true;
		}
		alunoService = new AlunoServiceHandlerImpl();
		cepHandler = new CEPHandler();
		rb = ResourceBundle.getBundle("msg");
		formatter = new CPFFormatter();
	}
	
	public void salvaAluno(){
		if(validaAluno()){
			formatter.unformat(aluno.getCpf());
			if(updateAluno){
				alunoService.update(aluno);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Aluno salvo com sucesso"));
			}else{
				aluno.setDataMatricula(Calendar.getInstance().getTime());
				alunoService.create(aluno);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Aluno salvo com sucesso"));
			}
			limpaTela();
		}
	}
	
	public void limpaTela(){
		aluno = new Aluno();
	}
	
	public boolean validaAluno(){
		boolean ret = true;
		if(aluno.getNome() == null || aluno.getNome().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronome")));
			ret = false;
		}
		if(aluno.getEmail() == null || aluno.getEmail().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroemail")));
			ret = false;
		}
		ret = verificaEndereco(aluno);
		if(aluno.getTelefone() == null || aluno.getTelefone().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errotelefone")));
			ret = false;
		}
		return ret;
	}
	
	public boolean verificaEndereco(Aluno endereco){
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
			validator.assertValid(aluno.getCpf());
			try{
				aluno = alunoService.findByCpf(formatter.unformat(aluno.getCpf()));
			}catch(HttpServerErrorException e){
				
			}
			
		}catch(InvalidStateException e){
			FacesContext.getCurrentInstance().addMessage("messageAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "CPF INVÁILDO"));
		}
	}
	
	public void buscaCEP(){
		try{
			cepHandler.getEndereco(aluno);
		}catch (Exception e){
			FacesContext.getCurrentInstance().addMessage("messageAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
