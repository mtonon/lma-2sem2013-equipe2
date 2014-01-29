package br.com.yaw.spgae.dao;

import java.util.List;
import br.com.yaw.spgae.model.Aluno;

public interface AlunoDAO {

	Aluno save(Aluno aluno);
	
	List<Aluno> getAll();
	
	Boolean remove(Aluno aluno);
	
	Aluno findById(Long id);

}
