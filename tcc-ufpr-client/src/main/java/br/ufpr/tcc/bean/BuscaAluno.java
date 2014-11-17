package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import br.ufpr.model.Aluno;
import br.ufpr.service.handler.impl.AlunoServiceHandlerImpl;

@ViewScoped
@ManagedBean(name = "buscaAlunoBean")
public class BuscaAluno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer tipoPesquisa;
	private Aluno aluno;
	private Aluno alunoSelecionado;
	private List<Aluno> lstAlunos;
	private ResourceBundle rb;
	private boolean renderInfo;
	private String nomeAluno;
	private AlunoServiceHandlerImpl alunoService;
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		alunoService = new AlunoServiceHandlerImpl();
		tipoPesquisa = 1;
		aluno = new Aluno();
		alunoSelecionado = new Aluno();
		lstAlunos = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
	}
	
	public void buscaAlunoPorNome(){
		lstAlunos = (List<Aluno>) alunoService.findByNome(nomeAluno);
	}
	
	public void buscaAlunoPorMatricula(){
		aluno = alunoService.getOne(aluno.getMatricula());
		if(aluno != null && aluno.getMatricula() != null){
			renderInfo = true;
		}
	}
	
	public void selecionaAluno(){
		aluno = alunoSelecionado;
		alunoSelecionado = new Aluno();
		renderInfo = true;
	}
	
	public String editaAluno(){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("editAluno", aluno);  
		return "cadastroAluno.xhtml?faces-redirect=true";
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





	public Aluno getAluno() {
		return aluno;
	}





	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}





	public List<Aluno> getLstAlunos() {
		return lstAlunos;
	}





	public void setLstAlunos(List<Aluno> lstAlunos) {
		this.lstAlunos = lstAlunos;
	}





	public boolean isRenderInfo() {
		return renderInfo;
	}





	public void setRenderInfo(boolean renderInfo) {
		this.renderInfo = renderInfo;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	
	
}
