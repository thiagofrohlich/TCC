package br.ufpr.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the aluno_disciplina database table.
 * 
 */
@Embeddable
public class AlunoDisciplinaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="aluno_matricula", insertable=false, updatable=false)
	private Integer alunoMatricula;

	@Column(name="disciplina_id", insertable=false, updatable=false)
	private Integer disciplinaId;

	public AlunoDisciplinaPK() {
	}
	public Integer getAlunoMatricula() {
		return this.alunoMatricula;
	}
	public void setAlunoMatricula(Integer alunoMatricula) {
		this.alunoMatricula = alunoMatricula;
	}
	public Integer getDisciplinaId() {
		return this.disciplinaId;
	}
	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AlunoDisciplinaPK)) {
			return false;
		}
		AlunoDisciplinaPK castOther = (AlunoDisciplinaPK)other;
		return 
			this.alunoMatricula.equals(castOther.alunoMatricula)
			&& this.disciplinaId.equals(castOther.disciplinaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.alunoMatricula.hashCode();
		hash = hash * prime + this.disciplinaId.hashCode();
		
		return hash;
	}
}