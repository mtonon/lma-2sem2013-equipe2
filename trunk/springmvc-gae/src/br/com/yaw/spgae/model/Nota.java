package br.com.yaw.spgae.model;

import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Nota implements Serializable{

	@Id
	private Long id;
	private String nome;
	private double valor;
	Key<UC> idUC;
	Key<Aluno> idAluno;
	
	public Nota() {}
	
	public Nota(String nome, Key<UC> idUC) {
		this.nome = nome;
		this.idUC = idUC;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Key<UC> getIdUC() {
		return idUC;
	}

	public void setIdUC(Key<UC> idUC) {
		this.idUC = idUC;
	}

	public Key<Nota> getKey() {
		return Key.create(Nota.class, id);
	}
	
	public Key<Aluno> getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Key<Aluno> idAluno) {
		this.idAluno = idAluno;
	}

	@Override
	public String toString()
	{
		return "Nota: \n\tID: " + id + "\n\tNome: " + nome + "\n\tidUC: " + idUC;
	}
}
