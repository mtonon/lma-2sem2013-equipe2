package br.com.yaw.spgae.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.yaw.spgae.dao.AlunoDAOObjectify;
import br.com.yaw.spgae.dao.AulaDAOObjectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UC implements Serializable{

	@Id
	private Long id;

	private String nome;
	private int semestre;
	private int ano;
	private int numProvas;
	private double pesoProvas;
	private int numTrabalhos;
	private double pesoTrabalhos;
	

	List<Key<Aluno>> idAlunos;

	public UC() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<Key<Aluno>> getIdAlunos() {
		return idAlunos;
	}

	public void setIdAlunos(List<Key<Aluno>> idAlunos) {
		this.idAlunos = idAlunos;
	}
	
	public int getNumProvas() {
		return numProvas;
	}

	public void setNumProvas(int numProvas) {
		this.numProvas = numProvas;
	}

	public double getPesoProvas() {
		return pesoProvas;
	}

	public void setPesoProvas(double pesoProvas) {
		this.pesoProvas = pesoProvas;
	}

	public int getNumTrabalhos() {
		return numTrabalhos;
	}

	public void setNumTrabalhos(int numTrabalhos) {
		this.numTrabalhos = numTrabalhos;
	}

	public double getPesoTrabalhos() {
		return pesoTrabalhos;
	}

	public void setPesoTrabalhos(double pesoTrabalhos) {
		this.pesoTrabalhos = pesoTrabalhos;
	}

	public List<Aluno> getAlunosDaUC()
	{
		AlunoDAOObjectify alunoObjetify = new AlunoDAOObjectify();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		for(int i = 0; i < idAlunos.size(); i++)
		{
			alunos.add(alunoObjetify.findById(idAlunos.get(i).getId()));
		}

		return alunos;
	}
	
	public List<Aula> getAulasDaUC()
	{
		AulaDAOObjectify aulaObjectify = new AulaDAOObjectify();
		List<Aula> todasAsAulas = aulaObjectify.getAll();
		ArrayList<Aula> aulas = new ArrayList<Aula>();
		
		for (Aula aula : todasAsAulas) {
			if( aula.getUc().getId() == this.id )
				aulas.add(aula);
		}
		
		return aulas;
	}

	public void addAlunoUC(Aluno aluno)
	{
		if (idAlunos == null) {
			idAlunos = new ArrayList<Key<Aluno>>();
		}
		
		idAlunos.add(aluno.getKey());
	}
	
	public Key<UC> getKey() {
		return Key.create(UC.class, id);
	}

	@Override
	public String toString()
	{
		String listaDeAlunos = "";
		Aluno alunoAux;

		if (idAlunos != null && idAlunos.size() != 0 ) {

			for(int i = 0; i < idAlunos.size(); i++)
			{
				AlunoDAOObjectify alunoObjectify = new AlunoDAOObjectify();
				alunoAux = alunoObjectify.findById(idAlunos.get(i).getId());

				listaDeAlunos += "\n\tID do aluno="+alunoAux.getId()+ 
						"\n\tNome do aluno="+alunoAux.getNome() +
						"\n\tRA do aluno="+alunoAux.getRa();
			}
			return "Aula: Id=" + id + ", Nome=" + nome + ", Semestre=" + semestre + ", Ano=" + ano + ", Num. provas=" + numProvas +
			", Peso provas=" + pesoProvas + ", Num. trabalhos=" + numTrabalhos + ", Peso trabalhos=" + pesoTrabalhos + "\nLista de alunos:" + listaDeAlunos;
		}
		
		return "Aula: Id=" + 0 + ", Nome=" + nome + ", Semestre=" + semestre + ", Ano=" + ano + ", Num. provas=" + numProvas +
				", Peso provas=" + pesoProvas + ", Num. trabalhos=" + numTrabalhos + ", Peso trabalhos=" + pesoTrabalhos + "\nLista de alunos:" + "VAZIA";
		
	}
}
