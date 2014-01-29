package br.com.yaw.spgae.dao;

import java.util.List;

import br.com.yaw.spgae.model.Aula;

public interface AulaDAO {
	Aula save(Aula aula);
	List<Aula> getAll();
	Boolean remove(Aula aula);
	Aula findById(Long id);
}
