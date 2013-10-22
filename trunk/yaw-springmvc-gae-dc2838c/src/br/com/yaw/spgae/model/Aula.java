package br.com.yaw.spgae.model;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

//@Entity
public class Aula implements Serializable{
	
	
	@Id
	private Long id;
	
	Set<Key<Aluno>> idAlunos;
	
	//falta relacionar os alunos com a presença e colocar o id da turma
	
	
	

}
