package fr.eni.filmotheque.bll.mock;

import java.util.ArrayList;
import java.util.List;
import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.bo.Participant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class FilmServiceBouchon implements IFilmService {
	// Attributs statiques pour gérer les valeurs à afficher et simuler les données
	// en base
	private static List<Film> lstFilms = new ArrayList<>();
	private static List<Genre> lstGenres = new ArrayList<>();
	private static List<Participant> lstParticipants = new ArrayList<>();
	private static List<Membre> lstMembres = new ArrayList<>();
	private static List<Avis> lstAvis = new ArrayList<>();
	private static int indexFilms = 1;

	// Représente la table en base de données des genres des films
	private static final String[] genres = { "Animation", "Science-fiction", "Documentaire", "Action", "Comédie",
			"Drame" };

	public FilmServiceBouchon() {
		simulationCoucheDALetDB();
	}

	@Override
	public List<Film> consulterFilms() {
		return lstFilms;
	}

	/**
	 * @return film si id correspond
	 * @return null si inconnu
	 */
	@Override
	public Film consulterFilmParId(long id) {
		return lstFilms.stream().filter(item -> item.getId() == id).findAny().orElse(null);
	}

	@Override
	public List<Genre> consulterGenres() {
		return lstGenres;
	}

	@Override
	public List<Participant> consulterParticipants() {
		return lstParticipants;
	}

	@Override
	public List<Membre> consulterMembres() {
		return lstMembres;
	}

	@Override
	public Genre consulterGenreParId(long id) {
		return lstGenres.stream().filter(item -> item.getId() == id).findAny().orElse(null);
	}

	/**
	 * @return participant si id correspond
	 * @return null si inconnu
	 */
	@Override
	public Participant consulterParticipantParId(long id) {
		return lstParticipants.stream().filter(item -> item.getId() == id).findAny().orElse(null);
	}

	@Override
	public List<Avis> consulterAvis(long idFilm) {
		return lstAvis;
	}

	@Override
	public void creerFilm(Film film) {
		// Sauvegarde du film
		film.setId((long) indexFilms++);
		lstFilms.add(film);
	}

	/**
	 * Cette méthode permet de simuler le stockage en base de données et la remontée
	 * d'information
	 */
	public void simulationCoucheDALetDB() {
		// Création de la liste des genres
		for (int index = 0; index < genres.length; index++) {
			lstGenres.add(new Genre((long) (index + 1), genres[index]));
		}

		// Création de la liste des participants aux films (acteurs et réalisateurs)
		// 3 réalisateurs dont 1 pour 2 films et 1 réalisateurs qui est aussi un acteur
		lstParticipants.add(new Participant(1L, "Spielberg", "Steven"));
		lstParticipants.add(new Participant(2L, "Cronenberg", "David"));
		lstParticipants.add(new Participant(3L, "Boon", "Dany"));

		// 2 acteurs par film et l'un d'eux dans 2 films
		lstParticipants.add(new Participant(4L, "Attenborough", "Richard"));
		lstParticipants.add(new Participant(5L, "Goldblum", "Jeff"));
		lstParticipants.add(new Participant(6L, "Davis", "Geena"));
		lstParticipants.add(new Participant(7L, "Rylance", "Mark"));
		lstParticipants.add(new Participant(8L, "Barnhill", "Ruby"));
		lstParticipants.add(new Participant(9L, "Merad", "Kad"));

		// Création de la liste de films
		// 4 films
		Film jurassicPark = new Film((long) indexFilms++, "Jurassic Park", 1993, 128,
				"Le film raconte l'histoire d'un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.");
		jurassicPark.setGenre(lstGenres.get(1));
		jurassicPark.setRealisateur(consulterParticipantParId(1));
		// Associer les acteurs
		jurassicPark.getActeurs().add(consulterParticipantParId(4));
		jurassicPark.getActeurs().add(consulterParticipantParId(5));
		lstFilms.add(jurassicPark);

		Film theFly = new Film((long) indexFilms++, "The Fly", 1986, 95,
				"Il s'agit de l'adaptation cinématographique de la nouvelle éponyme de l'auteur George Langelaan.");
		theFly.setGenre(lstGenres.get(1));
		theFly.setRealisateur(consulterParticipantParId(2));
		// Associer les acteurs
		theFly.getActeurs().add(consulterParticipantParId(5));
		theFly.getActeurs().add(consulterParticipantParId(6));
		lstFilms.add(theFly);

		Film theBFG = new Film((long) indexFilms++, "The BFG", 2016, 117,
				"Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.");
		theBFG.setGenre(lstGenres.get(4));
		theBFG.setRealisateur(consulterParticipantParId(1));
		// Associer les acteurs
		theBFG.getActeurs().add(consulterParticipantParId(7));
		theBFG.getActeurs().add(consulterParticipantParId(8));
		lstFilms.add(theBFG);

		Film bienvenueChezLesChtis = new Film((long) indexFilms++, "Bienvenue chez les Ch'tis", 2008, 106,
				"Philippe Abrams est directeur de la poste de Salon-de-Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d'obtenir une mutation sur la Côte d'Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord.");
		bienvenueChezLesChtis.setGenre(lstGenres.get(4));
		bienvenueChezLesChtis.setRealisateur(consulterParticipantParId(3));
		// Associer les acteurs
		bienvenueChezLesChtis.getActeurs().add(consulterParticipantParId(3));
		bienvenueChezLesChtis.getActeurs().add(consulterParticipantParId(9));
		lstFilms.add(bienvenueChezLesChtis);

		// Création d'un membre et un avis
		Membre membre1 = new Membre(1L, "Baille", "Anne-Lise", "abaille@campus-eni.fr", null);
		Membre membre2 = new Membre(1L, "Dupond", "Jean", "jean-dupond@campus-eni.fr", null);
		membre2.setAdmin(true);
		lstMembres.add(membre1);
		lstMembres.add(membre2);
		Avis avis1 = new Avis(1L, 4, "On rit du début à la fin", membre1);
		lstAvis.add(avis1);
		bienvenueChezLesChtis.getAvis().add(avis1);
	}
}
