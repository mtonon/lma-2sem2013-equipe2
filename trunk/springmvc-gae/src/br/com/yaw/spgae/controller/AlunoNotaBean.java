package br.com.yaw.spgae.controller;

import java.util.List;
import br.com.yaw.spgae.model.Nota;

public class AlunoNotaBean {
	
	Long idAluno;
	String nomeAluno;
	List<String> notasAluno;
	
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

	public List<String> getNotasAluno() {
		return notasAluno;
	}

	public void setNotasAluno(List<String> notasAluno) {
		this.notasAluno = notasAluno;
	}
	
}
