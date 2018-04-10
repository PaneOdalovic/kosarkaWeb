package app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Igrac;

@Repository
@Transactional
public class IgracRepo {

		@PersistenceContext
		EntityManager em;
		
		public Igrac saveClan(Igrac i){
			try{
				em.persist(i);
				return i;
			}
			catch(Exception e){
				return null;
			}
		}
}
