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
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une département
 * donné
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesDepartement extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws RecensementException {

		System.out.println("Veuillez saisir un numéro de département:");
		String nomDept = scanner.nextLine();

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		int nbVilles = 0;
		if(NumberUtils.isCreatable(nbVillesStr)) {
			nbVilles = Integer.parseInt(nbVillesStr);
		} else {
			throw new NumberFormatException("Le nombre de villes indiqué ne correspond pas à un nombre, réessayez.");
		}

		List<Ville> villesDept = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
				villesDept.add(ville);
			} else {
				throw new RecensementException("Le département n'a pas été trouvé, vérifiez votre code et recommencez.");
			}
		}

		Collections.sort(villesDept, new EnsemblePopComparateur(false));

		if (villesDept.size() > 0) {
			System.out.println("Les " + nbVilles + " villes les plus peuplées du département " + nomDept + " :");
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesDept.get(i);
				System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		} 
	}

}
