package br.com.yaw.spgae.dao;

import java.util.List;

import br.com.yaw.spgae.model.Nota;

public interface NotaDAO {

	Nota save(Nota nota);
	
	List<Nota> getAll();
	
	Boolean remove(Nota nota);
	
	Nota findById(Long id);

}
