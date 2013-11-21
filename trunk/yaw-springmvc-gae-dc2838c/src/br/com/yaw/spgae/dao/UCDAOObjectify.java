package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.yaw.spgae.model.UC;

import com.googlecode.objectify.Key;

@Repository
public class UCDAOObjectify implements Serializable, UCDAO {

	@Override
	public UC save(UC uc) {
		ofy().save().entity(uc).now();
		return uc;
	}

	@Override
	public List<UC> getAll() {
		return ofy().load().type(UC.class).list();
	}

	@Override
	public Boolean remove(UC uc) {
		ofy().delete().entity(uc).now();
		return true;
	}

	@Override
	public UC findById(Long id) {
		Key<UC> k = Key.create(UC.class, id);
		return ofy().load().key(k).get();
	}
}
