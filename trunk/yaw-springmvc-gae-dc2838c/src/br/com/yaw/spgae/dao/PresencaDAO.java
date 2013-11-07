package br.com.yaw.spgae.dao;

import java.util.List;
import br.com.yaw.spgae.model.Presenca;

public interface PresencaDAO {

	Presenca save(Presenca presenca);
	
	List<Presenca> getAll();
	
	Boolean remove(Presenca presenca);
	
	Presenca findById(Long id);

}
