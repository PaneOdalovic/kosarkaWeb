package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikJPARepo extends JpaRepository<Korisnik, String> {

}
