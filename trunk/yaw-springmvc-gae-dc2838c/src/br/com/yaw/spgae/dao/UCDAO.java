package br.com.yaw.spgae.dao;

import java.util.List;

import br.com.yaw.spgae.model.UC;

public interface UCDAO {

	UC save(UC uc);
	
	List<UC> getAll();
	
	Boolean remove(UC uc);
	
	UC findById(Long id);

}
