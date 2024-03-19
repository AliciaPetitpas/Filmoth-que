package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmJpaRepository extends JpaRepository<Film, Long> {
}
