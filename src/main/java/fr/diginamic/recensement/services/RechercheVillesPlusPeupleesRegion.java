package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;
import fr.diginamic.recensement.services.exceptions.RecensementException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une région
 * donnée
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesRegion extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws RecensementException {

		System.out.println("Veuillez saisir un nom de région:");
		String nomRegion = scanner.nextLine();

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		int nbVilles = 0;
		if(NumberUtils.isCreatable(nbVillesStr)) {
			nbVilles = Integer.parseInt(nbVillesStr);
		} else {
			throw new NumberFormatException("Ceci n'est pas un nombre, recommencez.");
		}

		List<Ville> villesRegions = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
				villesRegions.add(ville);
			} else {
				throw new RecensementException("La région n'a pas été trouvée, vérifiez l'orthographe et recommencez.");			}
		}

		Collections.sort(villesRegions, new EnsemblePopComparateur(false));
		System.out.println("Les " + nbVilles + " villes les plus peuplées de la région " + nomRegion + " sont :");
		if (villesRegions.size() > 0) {
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesRegions.get(i);
				System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		}

	}

}
