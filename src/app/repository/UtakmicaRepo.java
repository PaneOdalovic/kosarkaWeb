package app.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Utakmica;

@Repository
@Transactional
public class UtakmicaRepo {
	
	@PersistenceContext
	EntityManager em;
	
	public Utakmica saveUtakmice(Utakmica u){
		try{
			em.persist(u);
			return u;
		}
		catch(Exception e){
			return null;
		}
	}
}
