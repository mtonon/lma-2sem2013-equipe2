package br.com.yaw.spgae.model;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Turma implements Serializable{

	@Id
	private Long id;
	
	Set<Key<Aluno>> idAlunos;
	
	@NotNull @Size(min=5, max=200)
	private String nomeTurma;
	
	@NotNull @Min(value=1)
	private Integer quantidade;
	
	public Turma() {
	}
	
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
