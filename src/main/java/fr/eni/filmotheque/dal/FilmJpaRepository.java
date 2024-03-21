package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.dto.SearchParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmJpaRepository extends JpaRepository<Film, Long> {
    /**
     * Récupère les films selon le titre recherché
     * @param titre
     * @return une liste de films
     */
    List<Film> findByTitre(String titre);

    List<Film> findByTitreStartingWithOrTitreEndingWith(String titreStart, String titreEnd);

    @Query("select f from Film f " +
                "left join f.genre g " +
                "left join f.listeParticipant p " +
            "where (f.titre like %:#{#params.search}% " +
                "or f.realisateur.Nom like %:#{#params.search}% " +
                "or f.realisateur.Prenom like %:#{#params.search}% " +
                "or p.Prenom like %:#{#params.search}% " +
                "or p.Nom like %:#{#params.search}%) " +
                // Filtre le genre
            "and (:#{#params.genre} is NULL or :#{#params.genre} = '' or :#{#params.genre} = 0 " +
                "or f.genre.Id = :#{#params.genre}) " +
                // Filtre sur l'année de réalisation
            "and f.Annee <= :#{#params.anneeMax} and f.Annee >= :#{#params.anneeMin} " +
            "and f.Duree <= :#{#params.dureeMax} and f.Duree >= :#{#params.dureeMin} "
    )
    List<Film> recherche(SearchParams params);
}
