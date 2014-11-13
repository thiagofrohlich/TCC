package br.ufpr.tcc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.ufpr.model.Aluno;
import br.ufpr.model.Disciplina;


@ViewScoped
@ManagedBean(name = "matriculaBean")
public class MatriculaBean {

	private Integer tipoPesquisa;
	private Aluno aluno;
	private List<Aluno> lstAlunos;
	private ResourceBundle rb;
	private boolean renderInfo;
	private List<Disciplina> lstDisciplinas;
	private List<Disciplina> lstEscolhidas;
	private DualListModel<Disciplina> disciplinas;
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		tipoPesquisa = 1;
		aluno = new Aluno();
		lstAlunos = new ArrayList<>();
		lstDisciplinas = new ArrayList<>();
		lstEscolhidas = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
	}
	
	
	public void salva(){
		
	}
	
	public boolean verificaMatricula(){
		boolean ret = true;
		if(aluno == null || aluno.getMatricula() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Selecione um aluno para realizar a matrícula"));
			ret = false;
		}
		if(disciplinas.getTarget().isEmpty()){
			ret = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Selecione ao menos uma disciplina para realizar a matrícula"));
		}
		return ret;
	}

	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
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




	public List<Disciplina> getLstDisciplinas() {
		return lstDisciplinas;
	}




	public void setLstDisciplinas(List<Disciplina> lstDisciplinas) {
		this.lstDisciplinas = lstDisciplinas;
	}




	public List<Disciplina> getLstEscolhidas() {
		return lstEscolhidas;
	}




	public void setLstEscolhidas(List<Disciplina> lstEscolhidas) {
		this.lstEscolhidas = lstEscolhidas;
	}




	public DualListModel<Disciplina> getDisciplinas() {
		return disciplinas;
	}




	public void setDisciplinas(DualListModel<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
}
