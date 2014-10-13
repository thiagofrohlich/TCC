package br.ufpr.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the disciplina database table.
 * 
 */
@Entity
@NamedQuery(name="Disciplina.findAll", query="SELECT d FROM Disciplina d")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISCIPLINA_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCIPLINA_ID_GENERATOR")
	private Integer id;

	private String nome;

	private Integer periodo;

	private String turno;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to AlunoDisciplina
	@OneToMany(mappedBy="disciplina")
	private List<AlunoDisciplina> alunoDisciplinas;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	private Professor professor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Usuario updatedBy;

	public Disciplina() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<AlunoDisciplina> getAlunoDisciplinas() {
		return this.alunoDisciplinas;
	}

	public void setAlunoDisciplinas(List<AlunoDisciplina> alunoDisciplinas) {
		this.alunoDisciplinas = alunoDisciplinas;
	}

	public AlunoDisciplina addAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		getAlunoDisciplinas().add(alunoDisciplina);
		alunoDisciplina.setDisciplina(this);

		return alunoDisciplina;
	}

	public AlunoDisciplina removeAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		getAlunoDisciplinas().remove(alunoDisciplina);
		alunoDisciplina.setDisciplina(null);

		return alunoDisciplina;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Usuario getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Usuario updatedBy) {
		this.updatedBy = updatedBy;
	}

}