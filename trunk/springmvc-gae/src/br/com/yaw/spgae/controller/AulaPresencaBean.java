package br.com.yaw.spgae.controller;

import java.util.List;

import br.com.yaw.spgae.model.Aluno;

public class AulaPresencaBean {
	
	Long idAula;
	List<Aluno> alunosPresentesNaAula;
	
	public AulaPresencaBean() {}

	public Long getIdAula() {
		return idAula;
	}

	public void setIdAula(Long idAula) {
		this.idAula = idAula;
	}

	public List<Aluno> getAlunosPresentesNaAula() {
		return alunosPresentesNaAula;
	}

	public void setAlunosPresentesNaAula(List<Aluno> alunosPresentesNaAula) {
		this.alunosPresentesNaAula = alunosPresentesNaAula;
	}

}
