package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Igrac;
import model.Utakmica;

public interface UtakmicaJPARepo  extends JpaRepository<Utakmica, Integer>  {
	public List<Utakmica> findAll();
	
	@Query("SELECT u FROM Utakmica u where u.odigrana is 0")
	public List<Utakmica> vratiNeodigraneUtakmice();
	
	@Query("SELECT u FROM Utakmica u where u.odigrana is 1")
	public List<Utakmica> vratiOdigraneUtakmice();
}
