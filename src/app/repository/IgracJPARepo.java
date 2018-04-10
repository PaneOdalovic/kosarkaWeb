package app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Igrac;

@Repository
public interface IgracJPARepo extends JpaRepository<Igrac, Integer> {
	public List<Igrac> findAll();
	
	@Query("SELECT i FROM Igrac i where i.datumOtpustanja is null")
	public List<Igrac> vratiAktivneIgrace();
	
	@Query("SELECT i FROM Igrac i join fetch i.igras ig where ig.utakmica.idutakmica = :id")
	public List<Igrac> vratiIgraceZaUtakmicu(@Param("id")Integer id);
	
	@Query("SELECT  i FROM Igrac i where i.datumOtpustanja is null and i.idigrac not in (SELECT ig.igrac FROM Igra ig where ig.utakmica.idutakmica = :id) ")
	public List<Igrac> vratiIgraceKojiNisuNaUtakmicu(@Param("id")Integer id);
}
