package fr.diginamic.recensement.services;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées de France
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesFrance extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) {

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		int nbVilles = 0;
		if(NumberUtils.isCreatable(nbVillesStr)) {
			nbVilles = Integer.parseInt(nbVillesStr);
		} else {
			throw new NumberFormatException("Ceci n'est pas un nombre, recommencez.");
		}

		List<Ville> villes = recensement.getVilles();
		System.out.println("Les " + nbVilles + " villes les plus peuplées de France sont :");
		Collections.sort(villes, new EnsemblePopComparateur(false));
		for (int i = 0; i < nbVilles; i++) {
			Ville ville = villes.get(i);
			System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
		}
	}

}
