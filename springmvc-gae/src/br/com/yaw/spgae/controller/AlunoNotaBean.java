package br.com.yaw.spgae.controller;

import java.util.List;
import br.com.yaw.spgae.model.Nota;

public class AlunoNotaBean {
	
	Long idAluno;
	String nomeAluno;
	List<Nota> notasAluno;
	
	public AlunoNotaBean() {}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public List<Nota> getNotasAluno() {
		return notasAluno;
	}

	public void setNotasAluno(List<Nota> notasAluno) {
		this.notasAluno = notasAluno;
	}

}
