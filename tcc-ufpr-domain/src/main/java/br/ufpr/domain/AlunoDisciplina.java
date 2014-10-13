package br.ufpr.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aluno_disciplina database table.
 * 
 */
@Entity
@Table(name="aluno_disciplina")
@NamedQuery(name="AlunoDisciplina.findAll", query="SELECT a FROM AlunoDisciplina a")
public class AlunoDisciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlunoDisciplinaPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_curso")
	private Date dataCurso;

	private double nota;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	private Aluno aluno;

	//bi-directional many-to-one association to Disciplina
	@ManyToOne
	private Disciplina disciplina;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Usuario usuario;

	public AlunoDisciplina() {
	}

	public AlunoDisciplinaPK getId() {
		return this.id;
	}

	public void setId(AlunoDisciplinaPK id) {
		this.id = id;
	}

	public Date getDataCurso() {
		return this.dataCurso;
	}

	public void setDataCurso(Date dataCurso) {
		this.dataCurso = dataCurso;
	}

	public double getNota() {
		return this.nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}