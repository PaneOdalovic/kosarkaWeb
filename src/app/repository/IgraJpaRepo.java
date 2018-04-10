package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Igra;

public interface IgraJpaRepo extends JpaRepository<Igra, Integer> {
	
	
	
	@Query("SELECT i FROM Igra i where i.utakmica.idutakmica = :id")
	public List<Igra> vratiZaUtakmicu(@Param("id")Integer id);
	
	@Query("SELECT i FROM Igra i where i.utakmica.idutakmica = :utakmica and i.igrac.idigrac = :igrac")
	public Igra pronadjiIgru(@Param("igrac")Integer igrac,@Param("utakmica")Integer utakmica);
	
}
